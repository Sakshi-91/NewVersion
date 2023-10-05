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
repo_name=GetAccountDetails
echo $repo_name
cd $repo_name
file_list=(`find . -name *.cs|grep -vE '/obj/Debug/|/obj/Release/|/Properties/|/obj/Debug/'`)
cd ..
echo ${file_list[*]}
prompt=`cat ./prompt/prompt.txt|sed ':a;N;$!ba;s/\n/\\\n/g'|sed 's/\"/\\\"/g'`
echo $prompt
cd $repo_name
file_content=`cat ${file_list[*]}|grep -v '//'|tr '\r\n' ' '|sed 's/\"/\\\"/g'`
cd ..
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
	echo "$pure_code" > $i
done
awk -v RS="\`\`\`xml" -v ORS="" 'NR > 1 {print $0 > "pom.xml"}' <<< "$java_code"
pom=$(cat pom.xml|awk -v RS="\`\`\`" 'NR==1 {print $0}')
git_cmd=$(cat pom.xml|awk -v RS="\`\`\`" 'NR%2!=0 && NR!=1 {print $0}')
echo "$git_cmd" > git.txt
grep git git.txt
if [ $? -ne 0 ]
then
	git_cmd=$(cat pom.xml|awk -v RS="\`\`\`" 'NR>1 {print $0}')
	echo "$git_cmd"|awk -v FS="\`" '{print $2}'|grep '^git' > git.txt
fi
echo "$pom" > pom.xml
bash git.txt
​
		#echo ${code:0:len}|sed -e 's/\\n/\n/g' -e 's/\\\"/\"/g' 
		#[[ $zip_flag ]] && rm $i # if *.cs files are extracted from zip, so after conversion we can delete them because they are already present in zip 
	#[[ $zip_flag ]] && zip -q ${zip_file%.zip}_java.zip ${java_list[*]} && rm ${java_list[*]} # after compressing java files into zip, we can delete them because they are available in zip file.
