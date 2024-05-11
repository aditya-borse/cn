#!/usr/bin/env tclsh

# Create simple network with 3 nodes (2 senders and 1 receiver: TCP/FTP)

set ns [new Simulator]

$ns color 1 Blue 
$ns color 2 Red

# open tracefile
set tracefile [open out.tr w]
$ns trace-all $namfile

#open namfile
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
set sender1 [$ns node]
set sender2 [$ns node]
set receiver [$ns node]

# create links
$ns duplex-link $sender1 $receiver 2MB 20ms Droptail
$ns duplex-link $sender2 $receiver 2MB 20ms Droptail

# set first tcp connection
set tcp1 [new Agent/TCP]
$ns attach-agent $sender1 $tcp1
set ftp1 [new Application/FTP]
$ftp1 attach-agent $tcp1

# set second tcp connection
set tcp2 [new Agent/TCP]
$ns attach-agent $sender2 $tcp2
set ftp2 [new Application/FTP]
$ftp2 attach-agent $tcp2

# set sink 
set sink [new Agent/TCPSink]
$ns attach-agent $receiver $sink

# set connection
$ns connect $tcp1 $sink
$ns connect $tcp2 $sink

# schedule the ftp session 
$ns at 1.0 "ftp1 start"
$ns at 2.0 "ftp2 start"

# stop the session
$ns at 4.0 "ftp1 stop"
$ns at 5.0 "ftp2 stop"

$ns at 6.0 "finish"

$ns run