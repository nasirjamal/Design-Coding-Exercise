#!/bin/ksh
if [ ! -f check_status.conf ]
then
  echo "Conf file not found, Exiting..."
  exit 1
fi
. ./check_status.conf

if [ ! -f $file ]
then
  echo "Input file not found, Exiting ..."
  exit 1
fi

while true
do
	check=0
	while read line
	do
		#echo $line
		st=$line
		#echo $st | awk '{print "Server Name: " $1 " | Instances : " $2 " | Status: " $3}'
		server_name=`echo $st  | awk '{print $1}'`
		status=`echo $st | awk '{print $2}' | awk -F  "/" '{print $1}'`
		#echo $status
		if [ $status -gt 3 ] || [ $status -lt 0 ]; then
			echo "Incorrect instance input for $server_name Instance. Maximum allowed instance '3' found $status"
			continue
		fi
		case $status in
		1)
	        echo "AMBER Alert for ${server_name}"
		check=1
	        ;;
        	2)
	        echo "RED ALERT for ${server_name}"
		check=1
	        ;;
		3)
        	echo "RED ALERT for ${server_name}"
		check=1
        	;;
		esac
	done<$file
  if [ $check == 0 ]
  then
	echo "No Alerts"
  fi
  let time=$time_interval*60
  sleep $time
done
