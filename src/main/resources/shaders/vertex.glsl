#version 330

layout (location=0) in vec3 position;
layout (location=1) in vec2 texCoord;

uniform mat4 modelViewMatrix;

out vec2 outTexCoord;

out vec4 mvPos;

void main()
{
    mvPos = modelViewMatrix * vec4(position,1.0);
    gl_Position = modelViewMatrix * vec4(position,1.0);

    // passes on the texture cordinate
    outTexCoord = texCoord;

}