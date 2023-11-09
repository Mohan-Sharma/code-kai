#!/bin/bash

inodeUsage=0
diskUsage=0

for scanMode in inodes disk-usage ; do
            if [ "$scanMode" == inodes ] ; then
                duMode='--inodes'
                inodeUsage=$(df "$JENKINS_HOME" --output=ipcent | grep -o '[0-9]\+')
                echo "Current inode (number of files) usage level is $inodeUsage %"
            else
                duMode='-h'
                diskUsage=$(df "$JENKINS_HOME" --output=pcent | grep -o '[0-9]\+')
                echo "Current disk usage level is $diskUsage %"
            fi

            support="$JENKINS_HOME/userContent/.support/$scanMode"
            pathDepthFile="/tmp/pathDepthFile"
            result="$support/result"
            resultSorted="$support/result.sorted"
            cause="$support/cause"

            mkdir -p "$support"

            du "$duMode" "$JENKINS_HOME" 2>/dev/null | sort -hr > "$resultSorted"
            tr -d -c '/\n' < "$resultSorted" |  awk '{print length}' > "$pathDepthFile"
            paste -d' ' "$pathDepthFile" "$resultSorted" | sort -h > "$result"
            i=2; cp -f /dev/null "$cause"; prevPath="/var/jenkins_home"
            while : ; do
                sizeAndPath="$(grep "^$i" < "$result" | awk '{$1=""; print}' | sed -e 's/^[ \t]*//' | grep "$prevPath" | sort -hr 2>/dev/null | head -1)"
                prevPath="$(echo "$sizeAndPath" | awk '{$1=""; print $0}')"
                [ -z "$sizeAndPath" ] && break
                echo "$sizeAndPath"
                ((i++))
            done > "$cause"

            rm "$pathDepthFile"
done

if [ "$diskUsage" -gt 93 ] || [ "$inodeUsage" -gt 93 ]; then
        exit 2
elif [ "$diskUsage" -gt 85 ] || [ "$inodeUsage" -gt 85 ]; then
        exit 1
else
        exit 0
fi
