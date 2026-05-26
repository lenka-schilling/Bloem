#include "led_tcp_handler.h"

void bloemHandleTcpClient(WiFiClient& client, WiFiServer& tcpServer)
{
  if (!client || !client.connected())
  {
    client = tcpServer.available();
    return;
  }

  if (!client.available())
    return;

  String msg = "";
  unsigned long start = millis();

  while (client.available() && millis() - start < 50)
  {
    char c = client.read();

    if (c == '\n')
      break;

    msg += c;
    yield();
  }

  msg.trim();

  if (msg.length() == 0)
    return;

  Serial.println("Received: " + msg);

  if (msg == "LED:1")
  {
    leds(255, 255, 255);
  }
  else if (msg == "LED:0")
  {
    leds(0, 0, 0);
  }
  else if (msg.startsWith("RGB:"))
  {
    int first = msg.indexOf(',');
    int second = msg.lastIndexOf(',');

    if (first > 4 && second > first)
    {
      int r = msg.substring(4, first).toInt();
      int g = msg.substring(first + 1, second).toInt();
      int b = msg.substring(second + 1).toInt();

      leds(r, g, b);
    }
  }

  client.println("ACK");
}
