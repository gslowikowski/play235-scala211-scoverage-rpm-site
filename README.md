# play235-scala211-scoverage-rpm-site

## Introduction

Why this project - maven integration in an IDE is far better than sbt integration -( for example multi modules dependencies don't need to be installed + update dependencies without re-importing or running play eclipse ...) 

This project aim to industrialize play2-akka with maven

Produce reporting maven-site with scala doc and scoverage (code coverage)
Can be launch with mvn command or ./activator commands
Produce an RPM

    
### Build rpm package (tested on centos for centos)
./activator rpm:packageBin
    
                               