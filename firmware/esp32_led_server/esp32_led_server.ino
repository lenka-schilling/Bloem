/*
 * Bloem ESP32 LED server — TCP protocol (same as your friend's C# SocketClient).
 *
 * TCP port 2000, newline-terminated text commands:
 *   LED:1        → white
 *   LED:0        → off
 *   RGB:r,g,b    → set colour (e.g. RGB:86,107,95 for Moss green)
 *
 * This firmware drives a non-addressable RGB strip (3 PWM channels).
 * If your strip is common-anode, set INVERT_OUTPUT to 1 in leds.h.
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

  ledsInit();
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
