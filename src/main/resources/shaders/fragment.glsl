#version 430



uniform sampler2D texture_sampler;

in vec4 mvPos;
in vec2 outTexCoord;

out vec4 fragColor;


void main()
{

    fragColor = texture(texture_sampler,outTexCoord);
}