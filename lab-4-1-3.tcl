#!/usr/bin/env tclsh

# Q. Create a star topology TCP as well as UDP

# Create a simulator object
set ns [new Simulator]

$ns color 1 Blue
$ns color 2 Red

# Open tracefile
set tracefile [open out.tr w]
$ns trace-all $tracefile

# open namfile
set namfile [open out.nam w]
$ns namtrace-all $namfile

# define finish procedure
proc finish {} {
    global ns tracefile namfile
    $ns flush-trace
    close $tracefile
    close $namfile
    exit 0
}

# create required nodes
set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]
set n3 [$ns node]
set n4 [$ns node]

# create links
$ns duplex-link $n0 $n4 2MB 20ms Droptail
$ns duplex-link $n4 $n3 2MB 20ms Droptail
$ns duplex-link $n1 $n4 2MB 10ms Droptail
$ns duplex-link $n4 $n2 2MB 10ms Droptail

# set tcp connection 
set tcp [new Agent/TCP]
$ns attach-agent $n0 $tcp 
set sink [new Agent/TCPSink]
$ns attach-agent $n4 $sink
$ns connect $tcp $sink
$tcp set fid_ 1

# ftp over tcp 
set ftp [new Application/FTP]
$ftp attach-agent $tcp

# set udp connection
set udp [new Agent/UDP]
$ns attach-agent $n1 $udp
set null [new Agent/UDP]
$ns attach-agent $n2 $null
$ns connect $udp $null
$udp set fid_ 2

# cbr over udp 
set cbr [new Application/Traffic/CBR]
$cbr attach-agent $udp 
$cbr set packetSize_ 1000
$cbr set rate_ 0.01Mb 
$cbr set random_ false # meaning the packets will be generated at a fixed interval

# schedule the events
$ns at 0.5 "$cbr start"
$ns at 1.0 "$ftp start"
$ns at 9.0 "$ftp stop"
$ns at 9.5 "$cbr stop"

$ns at 10.0 "finish"

$ns run