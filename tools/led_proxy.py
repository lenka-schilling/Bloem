#!/usr/bin/env python3
"""
TCP proxy: Android emulator -> your PC -> ESP32

The emulator connects to 10.0.2.2:8888 on your PC. This script listens on
127.0.0.1:8888 and forwards each message to the ESP32 on port 2000
(same protocol as the EPS SocketClient).

Usage:
  1. Set ESP32_IP below (from Arduino Serial Monitor).
  2. Phone hotspot on; laptop + ESP32 connected to it.
  3. Run:  python tools/led_proxy.py
  4. Run the Bloem app in the Android Studio emulator.

Test from PowerShell on your PC:
  python tools/test_led.py
"""

import socket
import threading

ESP32_IP = "172.20.10.3"
ESP32_PORT = 2000
LISTEN_HOST = "127.0.0.1"
LISTEN_PORT = 8888


def relay(client: socket.socket):
    esp = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    try:
        esp.settimeout(5)
        esp.connect((ESP32_IP, ESP32_PORT))

        data = b""
        while b"\n" not in data:
            chunk = client.recv(256)
            if not chunk:
                break
            data += chunk

        if not data:
            return

        esp.sendall(data)
        ack = esp.recv(1024)
        if ack:
            client.sendall(ack)
        print(f"[proxy] {data.decode().strip()!r} -> ACK")
    except OSError as e:
        print(f"[proxy] error: {e}")
    finally:
        esp.close()
        client.close()


def main():
    server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
    server.bind((LISTEN_HOST, LISTEN_PORT))
    server.listen(8)
    print(f"TCP proxy: {LISTEN_HOST}:{LISTEN_PORT} -> {ESP32_IP}:{ESP32_PORT}")
    print("Emulator app uses 10.0.2.2:8888")
    print("Press Ctrl+C to stop.\n")

    while True:
        client, addr = server.accept()
        threading.Thread(target=relay, args=(client,), daemon=True).start()


if __name__ == "__main__":
    main()
