/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.validation;

/**
 *
 * @author ACER
 */
public class ValidateInput {

    public static String checkInputRangeMoney(String txtMinMoney, String txtMaxMoney) {
        String messErr = "";
        if (txtMaxMoney.isEmpty()) {
            messErr += "Please input max value\n";
        }

        if (txtMinMoney.isEmpty()) {
            messErr += "Please input min value\n";
        }

        if (messErr.isEmpty()) {
            float minMoney = 0;
            float maxMoney = 0;

            try {
                minMoney = Float.parseFloat(txtMinMoney);
            } catch (NumberFormatException e) {
                messErr += "Min value must be number\n";
            }

            try {
                maxMoney = Float.parseFloat(txtMaxMoney);
            } catch (NumberFormatException e) {
                messErr += "Max value must be number\n";
            }

            if (messErr.isEmpty()) {
                if (minMoney <= 0) {
                    messErr += "Min monney must be positive number\n";
                }

                if (maxMoney <= 0) {
                    messErr += "Max money must be positive number\n";
                }

                if (messErr.isEmpty()) {
                    if (minMoney >= maxMoney) {
                        messErr += "Min value must < Max value\n";
                    }
                }
            }
        }
        return messErr;
    }
}
