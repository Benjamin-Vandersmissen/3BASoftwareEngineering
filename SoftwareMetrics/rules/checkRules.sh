#!/bin/sh

echo Start
echo Code conventions
./pmd-bin-6.10.0/bin/run.sh pmd -d ../code -R rule_codeConventions.xml >> codestyle.log
echo \n\n
./pmd-bin-6.10.0/bin/run.sh pmd -d ../code -R rule_bestPractices.xml >> bestParactices.log
echo \n\n
./pmd-bin-6.10.0/bin/run.sh pmd -d ../code -R rule_design.xml >> design.log
echo \n\n
./pmd-bin-6.10.0/bin/run.sh pmd -d ../code -R rule_documentation.xml >> documentation.log
echo \n\n
./pmd-bin-6.10.0/bin/run.sh pmd -d ../code -R rule_errorProne.xml >> errorProne.log
echo \n\n
./pmd-bin-6.10.0/bin/run.sh pmd -d ../code -R rule_performance.xml >> performance.log
echo \n\n
echo Done
