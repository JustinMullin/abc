#ifdef GL_ES
#define LOWP lowp
precision mediump float;
#else
#define LOWP 
#endif

varying LOWP vec4 v_color;
varying vec2 v_texCoords;
uniform sampler2D u_texture;

uniform vec2 gameSize;

uniform float time;

float circle(vec2 p, vec2 v, float r) {
  return length(p - v) - r;
}

float rect(vec2 p, vec2 v, vec2 size) {
  vec2 d = max(v-p, p-(v+size));
  return length(max(vec2(0.0), d)) + min(0.0, max(d.x, d.y));
}

void main() {
	float aspectRatio = gameSize.x/gameSize.y;
  vec2 v = (2.0 * (gl_FragCoord.xy / gameSize) - 1.0) * vec2(aspectRatio, 1);

  float r = mix(circle(v, vec2(0, 0.2165), 0.5), rect(v, vec2(-0.35, -0.1335), vec2(0.7)), 0.5+0.5*sin(time*1.2));
  float g = mix(circle(v, vec2(-0.25, -0.2165), 0.5), rect(v, vec2(-0.6, -0.5675), vec2(0.7)), 0.5+0.5*sin(time*0.7));
  float b = mix(circle(v, vec2(0.25, -0.2165), 0.5), rect(v, vec2(-0.1, -0.5675), vec2(0.7)), 0.5+0.5*sin(time*1.65));

  gl_FragColor = vec4(
    -clamp(floor(r), -1.0, 0.0),
    -clamp(floor(g), -1.0, 0.0),
    -clamp(floor(b), -1.0, 0.0),
    1.0);

  /*gl_FragColor = vec4(
    min(abs(min(sign(d), 0.0) * floor(sin(d*150.0)) * max(1.0+5.0*d, 0.0)), 1.0),
    min(abs(max(sign(d), 0.0) * ceil(sin(d*150.0)) * max(1.0-5.0*d, 0.0)), 1.0),
    0, 1);*/
}