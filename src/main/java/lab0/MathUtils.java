package lab0;

class MathUtils {
    public boolean isNarcissistic(int number) {

        int length = (int) (Math.log10(number) + 1);
        int result = 0;

        int remainingNumber = number;
        for (int i = 0; i < length; i++) {
            int digit = remainingNumber % 10;
            remainingNumber = remainingNumber / 10;
            result += (int) Math.pow(digit, length);
        }

        return result == number;
    }
}
