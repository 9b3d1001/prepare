public class TrappingRainWater {

    public static void main(String[] args) {
        trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1});
    }

    public static int trap(int[] height) {
        int totalWaterTrapped = 0;
        int currentThreshold = 0;
        for(int i = 1; i < height.length - 1; i++) {
            if(height[i] < height[i-1] + currentThreshold) {
                currentThreshold = height[i-1] + currentThreshold - height[i];
                totalWaterTrapped += currentThreshold;
            } else {
                currentThreshold = 0;
            }
        }
        return  totalWaterTrapped - currentThreshold;
    }
}
