package leetcode_2020.JavaPractice.basic;

public enum EnumSample {

        HIGH  (3),  //calls constructor with value 3
        MEDIUM(2),  //calls constructor with value 2
        LOW   (1)   //calls constructor with value 1
; // semicolon needed when fields / methods follow

        private final int levelCode;

        EnumSample(int levelCode) {
            this.levelCode = levelCode;
        }
    public int getLevelCode() {
        return this.levelCode;
    }

}

