#!/usr/bin/bash
#set -x
convert_code()
{
#echo $1
a="sk-H8D3LLvmzJW5MLN35"
b="yppT3BlbkFJgZjtZ38o0"
c="R2DnDt3FENp"
api_key=$a$b$c
curl --silent $url \
		-H "Authorization: Bearer $api_key" \
		-H "Content-Type: application/json" \
		-d "{
		     \"model\": \"gpt-3.5-turbo\",
	    	     \"messages\": [{\"role\": \"user\", \"content\": \"$1\n$2\"}],
		     \"temperature\": 0.4
	            }" 
		#|awk -F\" '
		#$2=="content" {
		#		print $0
		#		}' \
		#|awk -F\"content\": '
		#	{
		#	   print $2
		#   	}' 
}
url=https://api.openai.com/v1/chat/completions
repo_url=$1
repo_name=`echo $repo_url|awk -F'/' '{printf "%s",$NF}'`
repo_name="./GetAccountDetails"
echo $repo_name
cd $repo_name
#file_list=(`find . -name *.cs|grep -vE '/obj/Debug/|/obj/Release/|/Properties/|/obj/Debug/' && find . -name *.wsdl`)
file_list=(`find . -name *.cs|grep -vE '/obj/Debug/|/obj/Release/|/Properties/|/obj/Debug/'`)
file_content=`cat ${file_list[*]}|grep -v '//'|tr '\r\n' ' '|sed 's/\"/\\\"/g'`
repo_name=`echo ${repo_name}|tr '/' ' '|tr '.' ' '`
repo_name=`echo ${repo_name}`
cd ..
echo ${file_list[*]}
prompt=`cat ./script/prompt_main.txt|sed ':a;N;$!ba;s/\n/\\\n/g'|sed 's/\"/\\\"/g'`
#prompt=`cat ./prompt/prompt_main.txt|sed ':a;N;$!ba;s/\n/\\\n/g'|sed 's/\"/\\\"/g'`
echo $prompt
​
mkdir -p code_conversion
cd code_conversion
convert_code "$prompt" "$file_content" > input.json
w_count=$(cat input.json|wc -c)
java_code=$(cat input.json |awk -v RS="\"content\": \"" 'NR>1 {print $0}'|awk -v RS="\"finish_reason\": \"stop\"" 'NR==1 {print $0}'|awk 'NR==1 {print $0}'|cut -c -$((w_count-3)))
#java_code=$(jq -r '.choices[].message.content' input.json)
#java_code=$(convert_code "$prompt" "$file_content"|jq -r '.choices[].message.content')
#echo "$java_code" > input.json
# Remove escape characters for double quotes
java_code=$(echo "$java_code" | sed 's/\\"/"/g')
java_code="${java_code//'\n'/$'\n'}"
#echo "$(java_code)"|tr '\\n' '\n' > input.json
# Create directory for Java files
file_names=$(grep -F .java: <<< "$java_code"|tr : ' '|cut -d. -f1)
echo ${file_names[*]}
# Use awk to split Java code into individual files
indx=0
awk -v RS="\`\`\`java" -v ORS="" -v ARR="${file_names[*]}" 'BEGIN {split(ARR,FILE_NAME," ")} NR > 1 {print $0 > FILE_NAME[NR-1] ".java"}' <<< "$java_code"
#cd java_files
for i in *.java
do
	pure_code=$(cat $i |awk -v RS="\`\`\`" NR==1'{print $0}')
	echo "$pure_code"|grep 'package com.example' >/dev/null && echo "$pure_code"|sed 's/com.example/com/g' > $i
	echo "$pure_code"|grep 'package com' >/dev/null && echo "$pure_code" > $i
	echo "$pure_code"|grep "package ${repo_name}" && echo "$pure_code"|sed 's/package /package com./g' > $i
	#echo start
	proj=`cat $i|grep '^package '|awk -v RS='.' '$1 ~ ";" {print $0}'|tr ';' ' '`
	#echo ${proj}
	#echo end
	#echo "$pure_code" > $i
done
#echo ${proj} here
awk -v RS="\`\`\`xml" -v ORS="" 'NR > 1 {print $0 > "pom.xml"}' <<< "$java_code"
pom=$(cat pom.xml|awk -v RS="\`\`\`" 'NR==1 {print $0}')
#git_cmd=$(cat pom.xml|awk -v RS="\`\`\`" 'NR%2!=0 && NR!=1 {print $0}')
#echo "$git_cmd" > git.txt
#grep git git.txt
#if [ $? -ne 0 ]
#then
#	git_cmd=$(cat pom.xml|awk -v RS="\`\`\`" 'NR>1 {print $0}')
#	echo "$git_cmd"|awk -v FS="\`" '{print $2}'|grep '^git' > git.txt
#fi
echo "$pom" > pom.xml
#mv *.java ../AccountDetails/src/main/java/com/example/accountdetails/
#mv pom.xml ../AccountDetails/
#proj=${repo_name}
#art=${proj}
proj=`echo ${proj}`
grp=com
echo ${proj} here
#spring init --build=maven --dependencies=web --group=${grp} --artifact-id=${art} --package-name=${grp}.${art} --name=${proj} ${proj}
cat << EOF > ${proj}Application.java
package com.${proj};
​
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
​
@SpringBootApplication
public class ${proj}Application {
​
	public static void main(String[] args) {
		SpringApplication.run(${proj}Application.class, args);
	}
​
}
EOF
mkdir -p "./${proj}/src/main/java/${grp}/${proj}/"
mv *.java "./${proj}/src/main/java/${grp}/${proj}/"
mv pom.xml "./${proj}/"
#spring init --build=maven --dependencies=web  ${repo_name}_java
#bash git.txt
