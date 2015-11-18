import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class WordFind {
	static int row;
	static int column;
	static char[][] grid;
	static int n;
	static char[][] wordBank;
	static int foundRow;
	static int foundColumn;
	static int currentWord;
	static char[][] output;
	static boolean found;
	
	public static void main(String[] args) throws FileNotFoundException{
		
		Scanner input = new Scanner(new FileReader("/Users/mjpodias/Documents/Programming/Workspace/WordsearchFind/src/input.txt"));
		
		row = input.nextInt(); 				//number of rows
		
		column = input.nextInt(); 			//number of columns
		
		grid = new char[row][column]; 		//grid for characters
		
		for (int r=0; r<row; r++){ 			//populates grid of chars
			grid[r] = input.next().toUpperCase().toCharArray();
			System.out.println(grid[r]);
		}
		
		System.out.println();
		
		n = input.nextInt();				//number of words
		
		wordBank = new char[n][]; 		//list of words
		
		for (int i=0; i<n; i++){ 			//populates list of words
			wordBank[i] = input.next().toUpperCase().toCharArray();
			System.out.println(wordBank[i]);
		}
		
		System.out.println();
		
		output = new char[n][];				//coordinate of first letter of words int grid
		
		for(int i=0; i<n; i++)
        {
            currentWord = i;
            found = false;
            
            for (int r=0; r<row; r++){
    			for (int c=0; c<column; c++){
    				if(wordBank[currentWord][0]==grid[r][c] && found==false){
    					foundRow = r;
    					foundColumn = c;
    					checkEveryDirection();
    				}
    			}
    		}
            
            if(found==false){
            	output[i] = "NOT FOUND".toCharArray();
            }
        }
		System.out.println();
		for(int i=0; i<n; i++){
			System.out.println(output[i]);
		}
	}
	
	private static void checkEveryDirection(){
		checkForwards();
		if (found==false) checkBackwards();
		if (found==false) checkUp();
		if (found==false) checkDown();
		if (found==false) checkDiagUpRight();
		if (found==false) checkDiagUpLeft();
		if (found==false) checkDiagDownRight();
		if (found==false) checkDiagDownLeft();
	}
	
	private static void checkForwards() {
		for(int i=0; i < wordBank[currentWord].length; i++){
			if(foundColumn + i >= column) {
			   if (wordBank[currentWord][i] != grid[foundRow][foundColumn - column + i]) {
				   return;
			   }
			}
			else{
				if (wordBank[currentWord][i] != grid[foundRow][foundColumn + i]) return;
			}
		}
		output[currentWord] = (foundRow + " " + foundColumn).toCharArray();
		found=true;
	}
	
	private static void checkBackwards() {
		for(int i=0; i < wordBank[currentWord].length; i++){
			if(foundColumn - i < 0) {
				if (wordBank[currentWord][i] != grid[foundRow][foundColumn - i + column]) {
					return;
				}
			}
			else {
				if (wordBank[currentWord][i] != grid[foundRow][foundColumn - i]) return;
			}
		}
		output[currentWord] = (foundRow + " " + foundColumn).toCharArray();
		found=true;
	}
	
	private static void checkUp() {
		for(int i=0; i < wordBank[currentWord].length; i++){
			if(foundRow - i < 0) {
				if (wordBank[currentWord][i] != grid[foundRow - i + row][foundColumn]) {
					return;
				}
			}
			else {
				if (wordBank[currentWord][i] != grid[foundRow - i][foundColumn]) return;
			}
		}
		output[currentWord] = (foundRow + " " + foundColumn).toCharArray();
		found=true;
	}
	
	private static void checkDown() {
		for(int i=0; i < wordBank[currentWord].length; i++){
			if(foundRow + i >= row) {
				if (wordBank[currentWord][i] != grid[foundRow + i - row][foundColumn]) {
					return;
				}
			}
			else {
				if (wordBank[currentWord][i] != grid[foundRow + i][foundColumn]) return;
			}
		}
		output[currentWord] = (foundRow + " " + foundColumn).toCharArray();
		found=true;
		
	}
	
	private static void checkDiagUpRight() {
		for(int i=0; i < wordBank[currentWord].length; i++){
			if(foundRow - i < 0 && foundColumn + i >= column) {
				if(wordBank[currentWord][i] != grid[foundRow - i + row][foundColumn + i - column])
				return;
			}
			else if(foundRow - i < 0) {
				if(wordBank[currentWord][i] != grid[foundRow - i + row][foundColumn + i])
				return;
			}
			else if(foundColumn + i >= column) {
				if(wordBank[currentWord][i] != grid[foundRow - i][foundColumn + i - column])
				return;
			}
			else {
				if(wordBank[currentWord][i] != grid[foundRow - i][foundColumn + i]) return;
			}
		}
		output[currentWord] = (foundRow + " " + foundColumn).toCharArray();
		found=true;
	}
	
	private static void checkDiagUpLeft() {
		for(int i=0; i < wordBank[currentWord].length; i++){
			System.out.println(currentWord + " i:" + i + " foundCol:" + foundColumn + " foundrow: " + foundRow);
			if(foundRow - i < 0 && foundColumn - i < 0){
				if(wordBank[currentWord][i] != grid[foundRow - i + row][foundColumn - i + column])
				return;
			}
			else if(foundRow - i < 0) {
				if(wordBank[currentWord][i] != grid[foundRow - i + row][foundColumn - i])
				return;
			}
			else if(foundColumn - i < 0) {
				if(wordBank[currentWord][i] != grid[foundRow - i][foundColumn - i + column])
				return;
			}
			else {
				if(wordBank[currentWord][i] != grid[foundRow - i][foundColumn - i]) return;
			}
		}
		output[currentWord] = (foundRow + " " + foundColumn).toCharArray();
		found=true;
	}
	
	private static void checkDiagDownRight() {
		for(int i=0; i < wordBank[currentWord].length; i++){
			if(foundRow + i >= row && foundColumn + i >= column) {
				if(wordBank[currentWord][i] != grid[foundRow + i - row][foundColumn + i - column])
				return;
			}
			else if(foundRow + i >= row) {
				if(wordBank[currentWord][i] != grid[foundRow + i - row][foundColumn + i])
				return;
			}
			else if(foundColumn + i >= column) {
				if(wordBank[currentWord][i] != grid[foundRow + i][foundColumn + i - column])
				return;
			}
			else {
				if(wordBank[currentWord][i] != grid[foundRow + i][foundColumn + i]) return;
			}
		}
		output[currentWord] = (foundRow + " " + foundColumn).toCharArray();
		found=true;
	}
	
	private static void checkDiagDownLeft() {
		for(int i=0; i < wordBank[currentWord].length; i++){
			if(foundRow + i >= row && foundColumn - i < 0) {
				if(wordBank[currentWord][i] != grid[foundRow + i - row][foundColumn - i + column])
				return;
			}
			else if(foundRow + i >= row) {
				if(wordBank[currentWord][i] != grid[foundRow + i - row][foundColumn - i])
				return;
			}
			else if(foundColumn - i < 0) {
				if(wordBank[currentWord][i] != grid[foundRow + i][foundColumn - i + column])
				return;
			}
			else {
				if(wordBank[currentWord][i] != grid[foundRow + i][foundColumn - i]) return;
			}
		}
		output[currentWord] = (foundRow + " " + foundColumn).toCharArray();
		found=true;
	}
}