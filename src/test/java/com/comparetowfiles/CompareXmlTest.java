package com.comparetowfiles;

import jdk.internal.org.xml.sax.SAXException;
import org.custommonkey.xmlunit.XMLUnit;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utilities.Constants;
import utilities.XmlHelper;

import java.io.*;
import java.util.List;

public class CompareXmlTest {
    //private static FileInputStream fis1;
    protected static FileInputStream fis1;
    protected static FileInputStream fis2;
    protected static BufferedReader source;
    protected static BufferedReader target;

    @BeforeTest
    public static void testSetUp() throws FileNotFoundException {

        // reading two xml file to compare in Java program
        fis1 = new FileInputStream(Constants.File_Path + Constants.File_One);
        fis2 = new FileInputStream(Constants.File_Path + Constants.File_Two);

        // using BufferedReader for improved performance
        source = new BufferedReader(new InputStreamReader(fis1));
        target = new BufferedReader(new InputStreamReader(fis2));

        //configuring XMLUnit to ignore white spaces
        XMLUnit.setIgnoreWhitespace(true);
    }

    @Test
    public static void testCompareTwoXmlFiles() throws SAXException, IOException, org.xml.sax.SAXException {
        List differences = XmlHelper.compareXML(source, target);
        XmlHelper.printDifferences(differences);

    }








}
