/*
 * Bloem ESP32 LED server — same protocol as the EPS desktop client.
 *
 * TCP port 2000, newline-terminated text commands:
 *   LED:1        → white
 *   LED:0        → off
 *   RGB:r,g,b    → set colour (e.g. RGB:86,107,95 for Moss green)
 *
 * 1. Set WIFI_SSID / WIFI_PASSWORD below.
 * 2. Upload this entire folder in Arduino IDE.
 * 3. Serial Monitor (115200) prints the IP — use it in LedConfig.kt and tools/led_proxy.py
 */

#include <WiFi.h>
#include "leds.h"
#include "led_tcp_handler.h"

// --- your Wi‑Fi (hotspot name/password) ---
const char* WIFI_SSID = "YOUR_HOTSPOT_NAME";
const char* WIFI_PASSWORD = "YOUR_HOTSPOT_PASSWORD";

WiFiServer tcpServer(BLOEM_TCP_PORT);
WiFiClient tcpClient;

void setup()
{
  Serial.begin(115200);

  pinMode(RED_PIN, OUTPUT);
  pinMode(GREEN_PIN, OUTPUT);
  pinMode(BLUE_PIN, OUTPUT);
  leds(0, 0, 0);

  WiFi.mode(WIFI_STA);
  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);
  Serial.print("Connecting to WiFi");
  while (WiFi.status() != WL_CONNECTED)
  {
    delay(500);
    Serial.print(".");
  }
  Serial.println();
  Serial.print("TCP LED server ready at ");
  Serial.print(WiFi.localIP());
  Serial.print(":");
  Serial.println(BLOEM_TCP_PORT);

  tcpServer.begin();
}

void loop()
{
  bloemHandleTcpClient(tcpClient, tcpServer);
}
