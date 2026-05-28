#include "leds.h"

static const int CH_R = 0;
static const int CH_G = 1;
static const int CH_B = 2;

static int clamp8(int v) {
  if (v < 0) return 0;
  if (v > 255) return 255;
  return v;
}

static int maybeInvert(int v) {
#if INVERT_OUTPUT
  return 255 - v;
#else
  return v;
#endif
}

void ledsInit()
{
  ledcSetup(CH_R, PWM_FREQ_HZ, PWM_RES_BITS);
  ledcSetup(CH_G, PWM_FREQ_HZ, PWM_RES_BITS);
  ledcSetup(CH_B, PWM_FREQ_HZ, PWM_RES_BITS);

  ledcAttachPin(RED_PIN, CH_R);
  ledcAttachPin(GREEN_PIN, CH_G);
  ledcAttachPin(BLUE_PIN, CH_B);
}

void leds(int red, int green, int blue)
{
  const int r = maybeInvert(clamp8(red));
  const int g = maybeInvert(clamp8(green));
  const int b = maybeInvert(clamp8(blue));

  ledcWrite(CH_R, r);
  ledcWrite(CH_G, g);
  ledcWrite(CH_B, b);

  Serial.print("R:");
  Serial.print(r);
  Serial.print(" G:");
  Serial.print(g);
  Serial.print(" B:");
  Serial.println(b);
}
