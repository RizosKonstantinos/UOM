import javax.swing.*;
import java.util.ArrayList;

public class VariableByteCode {

    public static void main(String[] args) {

        int block = 8;

        ArrayList<StringBuilder> bytes = new ArrayList<>(); // This list will store all the 8bit blocks

        String input = JOptionPane.showInputDialog("Enter an integer > 0");
        int inputInteger = Integer.parseInt(input);
        StringBuilder inputBinary = new StringBuilder();
        inputBinary.append(Integer.toBinaryString(inputInteger));   // Converts the integer input into binary
        String stringKeeper = inputBinary.toString(); // This variable just keeps the string of binary to print it later for the user

        StringBuilder inputBinaryReverse;
        inputBinaryReverse = inputBinary.reverse(); // Reverse the inputBinary so that the processing will go from the end towards the beginning

        int index = 0;
        double numOfBytes = (int)Math.ceil((double)inputBinary.length()/(block-1)); // Calculates how many bytes will be needed
        int remainder = inputBinaryReverse.length(); // This variable is used to calculate how many bits are left for the process to end
        boolean firstByte = true;
        for(int i = 0; i<numOfBytes; i++){
            // if only 1 byte is needed
            if(numOfBytes==1){
                bytes.add(vbCodeForOneByte(inputBinary,block,inputBinaryReverse));
            }
            else{
                StringBuilder subString;
                // for the 1st byte of the n byte binary
                if(i==0){
                    bytes.add(vbCodeForNBytes(inputBinaryReverse,index,firstByte));
                    firstByte=false;
                    index+=7;
                }
                else{
                    // for the rest of the bytes
                    if(remainder>=7) {
                        bytes.add(vbCodeForNBytes(inputBinaryReverse,index,firstByte));
                        index += 7;
                    }
                    else{
                        subString =stringAppend(inputBinaryReverse,index,remainder);
                        int diff= block-subString.length();
                        for(int j=0; j<diff; j++){
                            subString.append(0);
                        }
                        bytes.add(subString);
                    }
                }
                remainder-=7;
            }
        }
        StringBuilder newString = new StringBuilder();
        for (StringBuilder aByte : bytes) {
            newString.append(aByte);
            newString.append(" ");
        }
        String r = newString.reverse().toString();
        JOptionPane.showMessageDialog(null,"Decimal: "+input+"\nBinary: "+stringKeeper+"\nVB-code: "+r);
    }

    // Method for processing an input that is less than 1 byte
    static StringBuilder vbCodeForOneByte(StringBuilder input, int block,StringBuilder inputReverse){
        int diff = block - input.length();  // calculates how many zero-bits must be added to the binary input so that
                                            // its length is 7
        for(int j=0; j<diff-1; j++){
            inputReverse.append(0);
        }
        inputReverse.append(1);
        return inputReverse;
    }

    // Method for processing an input that is more than 1 byte
    static StringBuilder vbCodeForNBytes(StringBuilder inputReverse, int index, boolean firstByte){
        StringBuilder sb;
        if(firstByte){
            sb = stringAppend(inputReverse,index,7);
            sb.append(1);
        }
        else{
            sb = stringAppend(inputReverse,index,7);
            sb.append(0);
        }
        return sb;
    }

    // Method that appends the binary to a string
    static StringBuilder stringAppend(StringBuilder inputReverse, int index, int flag){
        StringBuilder sb = new StringBuilder();
        for (int j=index; j<index+flag; j++) {
            sb.append(inputReverse.charAt(j));
        }
        return sb;
    }
}