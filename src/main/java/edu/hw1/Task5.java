package edu.hw1;

class Task5 {

    private Task5() {}


    static public boolean isPalindromeDescendant(int number) {
        String str = String.valueOf(number);
        if (str.length() < 2) {
            return false;
        }

        for (int i = 0; i < str.length() / 2; i++) {
            if (str.charAt(i) != str.charAt(str.length() - i - 1)) {

                String descendant = "";
                for (int j = 0; j + 1 < str.length(); j += 2) {
                    int z1 = str.charAt(j) - '0';
                    int z2 = str.charAt(j + 1) - '0';
                    descendant = descendant.concat(String.valueOf(z1 + z2));
                }
                if (str.length() % 2 == 1) {
                    descendant = descendant.concat(String.valueOf(str.charAt(str.length() - 1)));
                }

                return isPalindromeDescendant(Integer.parseInt(descendant));
            }
        }

        return true;
    }
}
