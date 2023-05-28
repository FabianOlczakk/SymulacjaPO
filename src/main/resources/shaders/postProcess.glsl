#version 430
layout(local_size_x = 16, local_size_y = 16, local_size_z = 1) in;
layout(rgba32f, binding = 0) uniform image2D img_output;

uniform int width;
uniform int height;

uniform float deltaTime;
uniform float diffuseSpeed;
uniform float evaporateSpeed;

int blurSize = 1;

void main() {
    ivec3 id = ivec3(gl_GlobalInvocationID);
    if(id.x < 0 || id.x >= width || id.y < 0 || id.y >= height) {
        return;
    }

    vec4 originalValue = imageLoad(img_output,id.xy);

    vec4 sum = vec4(0,0,0,0);
    for(int offsetX = -blurSize; offsetX<=blurSize;offsetX++)
    {
        for(int offsetY = -blurSize; offsetY <= blurSize; offsetY++)
        {
            int sampleX = id.x + offsetX;
            int sampleY = id.y + offsetY;

            if(sampleX >= 0 && sampleX < width && sampleY >= 0 && sampleY < height)
            {
                sum += imageLoad(img_output,ivec2(sampleX,sampleY));
            }
        }
    }

    vec4 blurResult = (sum * 1) / pow(blurSize * 2 + 1,2);

    vec4 diffuseValue = mix(originalValue, blurResult, diffuseSpeed * deltaTime);

    vec4 diffuseAndEvaporatedValue = max(vec4(0,0,0,0), diffuseValue - evaporateSpeed * deltaTime);

    imageStore(img_output, id.xy, diffuseAndEvaporatedValue);


}