package scanner;

/**
 * Scanner interface for CMinusScanner.
 * @author Kaicheng Ye, Elijah Solokha, Samuel Church
 * @version 1.0
 * File: Scanner.java
 * Created: Feb. 2023
 * © Copyright Cedarville University, its Computer Science faculty, and the
 * authors.  All rights reserved.
 * 
 * Description: Scanner interface that the CMinusScanner or
 * any other scanner has to implement. 
 */
public interface Scanner {
    public Token getNextToken();
    public Token viewNextToken();
}
