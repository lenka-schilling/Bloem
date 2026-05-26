#!/usr/bin/env python3
"""Send one RGB command through the PC proxy (same path as the emulator)."""

import socket

PROXY_HOST = "127.0.0.1"
PROXY_PORT = 8888

def send(command: str):
    with socket.create_connection((PROXY_HOST, PROXY_PORT), timeout=5) as s:
        s.sendall(command.encode())
        ack = s.recv(1024).decode().strip()
        print(f"Sent: {command.strip()!r}  Reply: {ack!r}")

if __name__ == "__main__":
    send("RGB:255,0,0\n")
