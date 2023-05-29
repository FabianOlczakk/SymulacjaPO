#version 430
layout(local_size_x = 8, local_size_y = 8, local_size_z = 1) in;
layout(rgba32f, binding = 0) uniform image2D img_output;

layout(std430, binding = 2) buffer Pos {
    vec4 agent[];
};

uniform vec4 AColor;
uniform float sensorAngleSpacingA;
uniform float turnSpeedA;
uniform float sensorOffsetDistA;
uniform int sensorSizeA;
uniform vec4 BColor;
uniform float sensorAngleSpacingB;
uniform float turnSpeedB;
uniform float sensorOffsetDistB;
uniform int sensorSizeB;
uniform float deltaTime;
uniform int width;
uniform int height;

float sensorAngleSpacing;
float turnSpeed;
float sensorOffsetDist;
int sensorSize;
int blurSize = 1;

float rand(vec2 co){
    return fract(sin(dot(co.xy ,vec2(12.9898,78.233))) * 43758.5453);
}

uint hash(uint state) {
    state ^= 2747636419u;
    state *= 2654435769u;
    state ^= state >> 16;
    state *= 2654435769u;
    state ^= state >> 16;
    state *= 2654435769u;
    return state;
}

float scaleRandom10(float random) {
    return random/4294967295.0;
}

float sense(vec4 agentT, float sensorAngleSpacing) {
    float sensorAngle = agentT.z + sensorAngleSpacing;
    vec2 sensorDir = vec2(cos(sensorAngle), sin(sensorAngle));
    ivec2 sensorCentre = ivec2(agentT.xy + sensorDir * sensorOffsetDist);
    float sum = 0;

    for(int offsetX = -sensorSize; offsetX <= sensorSize; offsetX++)
    {
        for(int offsetY = -sensorSize; offsetY <= sensorSize; offsetY++)
        {
            ivec2 pos = sensorCentre + ivec2(offsetX,offsetY);

            if (pos.x >= 0 && pos.x < width && pos.y >= 0 && pos.y < height)
            {
                if(agentT.w == 0)
                {
                    sum += imageLoad(img_output,pos).z - imageLoad(img_output,pos).x;
                }
                else
                {
                    sum += imageLoad(img_output,pos).x - imageLoad(img_output,pos).z;
                }
            }
        }
    }
    return sum;
}

void main() {
    uvec3 size = gl_NumWorkGroups * gl_WorkGroupSize;

    uint id =   gl_GlobalInvocationID.z * size.x * size.y +
                gl_GlobalInvocationID.y * size.x +
                gl_GlobalInvocationID.x;

    if( agent[id].w == 0) {
        sensorAngleSpacing = sensorAngleSpacingA;
        turnSpeed = turnSpeedA;
        sensorOffsetDist = sensorOffsetDistA;
        sensorSize = sensorSizeA;
    }
    else
    {
        sensorAngleSpacing = sensorAngleSpacingB;
        turnSpeed = turnSpeedB;
        sensorOffsetDist = sensorOffsetDistB;
        sensorSize = sensorSizeB;
    }

    uint random = hash(uint(agent[id].y) * width + uint(agent[id].x) + hash(id));

    float weightForward = sense(agent[id], 0);
    float weigthLeft = sense(agent[id], sensorAngleSpacing);
    float weigthRight = sense(agent[id], -sensorAngleSpacing);

    float randomSteerStrength = scaleRandom10(random);

    if(weightForward > weigthLeft && weightForward > weigthRight)
    {
        agent[id].z += 0;
    }
    else if (weightForward < weigthLeft && weightForward < weigthRight)
    {
        agent[id].z += (randomSteerStrength - 0.5f) * 2 * turnSpeed * deltaTime;
    }
    else if (weigthRight > weigthLeft)
    {
        agent[id].z -= randomSteerStrength * turnSpeed * deltaTime;
    }
    else if (weigthRight < weigthLeft)
    {
        agent[id].z += randomSteerStrength * turnSpeed * deltaTime;
    }

    vec2 direction = vec2(cos(agent[id].z),sin(agent[id].z));
    vec2 newPos = vec2(agent[id].x,agent[id].y) + direction;

    if(newPos.x < 0 || newPos.x >= width || newPos.y < 0 || newPos.y >= height) {
        newPos.x = min(width - 1, max(0, newPos.x));
        newPos.y = min(height - 1, max(0, newPos.y));
        agent[id].z = scaleRandom10(random) * 2 * 3.14159;
    }

    agent[id].x = newPos.x;
    agent[id].y = newPos.y;

    vec4 oldCol = imageLoad(img_output, ivec2(newPos.x,newPos.y));
    vec4 newCol;

    // output to a specific pixel in the image
    if( agent[id].w == 0) {
        newCol = AColor;
    }
    else
    {
        newCol = BColor;
    }

    imageStore(img_output, ivec2(newPos.x,newPos.y), newCol);
}

