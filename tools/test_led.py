#!/usr/bin/env python3
"""Send RGB commands through the PC proxy (same path as the emulator)."""

import socket
import time

PROXY_HOST = "127.0.0.1"
PROXY_PORT = 8888

def send(command: str):
    with socket.create_connection((PROXY_HOST, PROXY_PORT), timeout=5) as s:
        s.sendall(command.encode())
        ack = s.recv(1024).decode().strip()
        print(f"Sent: {command.strip()!r}  Reply: {ack!r}")

if __name__ == "__main__":
    # Slow sequence so you can see each color.
    for cmd, label in [
        ("RGB:255,0,0\n", "RED"),
        ("RGB:0,255,0\n", "GREEN"),
        ("RGB:0,0,255\n", "BLUE"),
        ("RGB:255,255,255\n", "WHITE"),
        ("RGB:0,0,0\n", "OFF"),
    ]:
        print(f"\n--- {label} ({cmd.strip()}) ---")
        send(cmd)
        time.sleep(1.5)
