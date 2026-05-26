#pragma once

#include <WiFi.h>
#include "leds.h"

#define BLOEM_TCP_PORT 2000

void bloemHandleTcpClient(WiFiClient& client, WiFiServer& tcpServer);
