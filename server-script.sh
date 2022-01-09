#!/bin/bash

# Using server script from aroxu; Which is fork from monun's. See his GitHub for more information. (https://github.com/arxou/server-script/)
# Original script is monun's server-script; Which is under MIT License. See his GitHub for more information. (https://github.com/monun/server-script/)

# Author BaeHyeonWoo

download() {
  wget -c --content-disposition -P "$2" -N "$1" 2>&1 | tail -1
}

if [[ "$OSTYPE" == "linux-gnu"* ]]; then
  server=https://github.com/aroxu/server-script/releases/latest/download/server_linux_x64.zip
elif [[ "$OSTYPE" == "msys" ]]; then
  server=https://github.com/aroxu/server-script/releases/latest/download/server_windows_x64.exe.zip
else
  echo "Unsupported OS. Please check your OSTYPE and try again."
  exit
fi

if type -p wget
then
    echo "Found wget Package"
else
    echo "wget was not found on this machine. Please install with your Package Manager. Exiting..."
    exit
fi

if type -p bsdtar
then
    echo "Found LibArchive-Tools Package"
else
    echo "LibArchive-Tools was not found on this machine. Please install with your Package Manager. Exiting..."
    exit
fi

for i in "${server[@]}"; do
  download_result=$(download "$i" "./.server")
  echo "$download_result <- $i"
done


if [ -d "./.server" ]
then
    cd .server || return
else
    mkdir .server
    cd .server || return
fi

if [ -f "server_linux_x64.zip" ]
then
  bsdtar -xf server_linux_x64.zip -C "./"
  rm -rf ./server_linux_x64.zip
  chmod +x ./server
  ./server
elif [ -f "server_windows_x64.exe.zip" ]
then
  bsdtar -xf server_windows_x64.exe.zip -C "./"
  rm -rf ./server_windows_x64.exe.zip
  chmod +x ./server.exe
  ./server.exe
else
  echo "Something went wrong! Try manually download server from: https://github.com/arxou/server-script/releases."
  echo "Exiting..."
  exit
fi

echo "Exiting..."
exit