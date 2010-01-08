package org.apache.maven.surefire.its;


import org.apache.maven.it.Verifier;
import org.apache.maven.it.util.ResourceExtractor;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * Test simple TestNG suite XML file
 * 
 * @author <a href="mailto:dfabulich@apache.org">Dan Fabulich</a>
 * 
 */
public class CheckTestNgSuiteXmlIT
    extends AbstractSurefireIntegrationTestClass
{
    public void testTestNgSuiteXml ()
        throws Exception
    {
        File testDir = ResourceExtractor.simpleExtractResources( getClass(), "/testng-suite-xml" );

        Verifier verifier = new Verifier( testDir.getAbsolutePath() );
        List goals = getInitialGoals();
        goals.add( "test" );
        verifier.executeGoals( goals );
        verifier.verifyErrorFreeLog();
        verifier.resetStreams();
        
        HelperAssertions.assertTestSuiteResults( 1, 0, 0, 0, testDir );
    }
    
    public void testTestNgSuiteXmlForkModeAlways()
        throws Exception
    {
        File testDir = ResourceExtractor.simpleExtractResources( getClass(), "/testng-suite-xml" );

        Verifier verifier = new Verifier( testDir.getAbsolutePath() );
        List goals = getInitialGoals();
        goals.addAll( Arrays.asList( new String[] {"test", "-DforkMode=always" } ) );
        verifier.executeGoals( goals );
        verifier.verifyErrorFreeLog();
        verifier.resetStreams();

        HelperAssertions.assertTestSuiteResults( 1, 0, 0, 0, testDir );
    }
}