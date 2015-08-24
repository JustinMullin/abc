#ifdef GL_ES
#define LOWP lowp
precision mediump float;
#else
#define LOWP 
#endif

#define PI 3.1415926535897932384626433832795

varying LOWP vec4 v_color;
varying vec2 v_texCoords;
uniform sampler2D u_texture;

uniform vec2 ballPosition;
uniform vec3 lightPosition;

#define EYE_POSITION vec3(0, 5, 0)
#define SHININESS 24.0

void main() {
  vec4 texColor = texture2D(u_texture, v_texCoords);

  float dis = length(v_texCoords-vec2(0.5, 0.5));

  vec3 normal = normalize(vec3(
    sin((v_texCoords.x-0.5)*PI),
    sin((dis*2.0+0.5)*PI)*0.5+0.5,
    sin((v_texCoords.y-0.5)*PI)));

  vec3 surfacePosition = vec3(ballPosition.x, 0, ballPosition.y) + normal*0.5;

  vec3 eyeDirection = normalize(EYE_POSITION - surfacePosition);
  vec3 lightDirection = normalize(lightPosition - surfacePosition);

  vec3 h = normalize(eyeDirection + lightDirection);
  float specular = pow(max(0.0, dot(normal, h)), SHININESS);

  float ambient = 0.4;
  float diffuse = 0.6*clamp(dot(normal, lightDirection), 0.0, 1.0);

  gl_FragColor = vec4(v_color.rgb * (ambient+diffuse+specular*0.75), texColor.a);
}