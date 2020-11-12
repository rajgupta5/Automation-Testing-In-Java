
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

## Setting Java versions using Jenv ##
- brew install jenv
- echo 'export PATH="$HOME/.jenv/bin:$PATH"' >> ~/.zshrc
- echo 'eval "$(jenv init -)"' >> ~/.zshrc
- jenv add <jdk_path>
- jenv versions
- jenv remove <jdk version>


## Uninstall ##
- cd /Library/Java/JavaVirtualMachines/
- sudo rm -rf amazon-corretto-11.jdk


## Configure IntelliJ ##
![IntelliJ Configure](https://github.com/rajgupta5/Automation-Testing-In-Java/blob/main/ProjectStructureForNewProjects.png)


## Working with multiple Java versions in MacOS ##
https://medium.com/@brunofrascino/working-with-multiple-java-versions-in-macos-9a9c4f15615a
https://www.jenv.be/
