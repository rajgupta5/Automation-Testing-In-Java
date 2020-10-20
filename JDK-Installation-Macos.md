
## Java Setup - Install ##
[https://aws.amazon.com/corretto/]
[https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/macos-install.html]

- /usr/libexec/java_home
- /usr/libexec/java_home -V
- cat ~/.bash_profile
- export JAVA_HOME=$(/usr/libexec/java_home)
- export MAVEN_HOME=/Users/rajkgupta/softwares/apache-maven-3.6.0
- /Library/Java/JavaVirtualMachines/jdk1.8.0_201.jdk/Contents/Home
- /Library/Java/JavaVirtualMachines/amazon-corretto-11.jdk/Contents/Home
- export JAVA_HOME=/Library/Java/JavaVirtualMachines/amazon-corretto-11.jdk/Contents/Home

## Uninstall ##
- cd /Library/Java/JavaVirtualMachines/
- sudo rm -rf amazon-corretto-11.jdk


## Configure IntelliJ ##
![IntelliJ Configure](https://github.com/rajgupta5/Automation-Testing-In-Java/blob/main/ProjectStructureForNewProjects.png)
