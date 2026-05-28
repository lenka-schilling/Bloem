#pragma once

#include <Arduino.h>

#define RED_PIN   25
#define GREEN_PIN 27
#define BLUE_PIN  26

// If your strip is common-anode, set to 1 (it will invert PWM).
#define INVERT_OUTPUT 0

// ESP32 LEDC PWM config (stable PWM vs analogWrite)
#define PWM_FREQ_HZ 5000
#define PWM_RES_BITS 8   // keep 0-255 values

void ledsInit();
void leds(int red, int green, int blue);
