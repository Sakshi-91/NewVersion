#!/usr/bin/bash
#set -x
convert_code()
{
a="sk-H8D3LLvmzJW5MLN35"
b="yppT3BlbkFJgZjtZ38o0"
c="R2DnDt3FENp"
api_key=$a$b$c
#echo $1
curl --silent $url \
		-H "Authorization: Bearer $api_key" \
		-H "Content-Type: application/json" \
		-d "{
		     \"model\": \"gpt-3.5-turbo\",
	    	     \"messages\": [{\"role\": \"user\", \"content\": \"$1\n$2\"}],
		     \"temperature\": 0.5
	            }" 
}

#url=https://jsonplaceholder.typicode.com/posts
url=https://api.openai.com/v1/chat/completions
repo_name="./GetAccountDetails"
cd $repo_name
file_list=(`find . -name *.java && find ../testsuitescript -name *.xml`)
echo ${file_list[*]}
file_content=`cat ${file_list[*]}|grep -v '//'|tr '\r\n' ' '|sed 's/\"/\\\"/g'`
cd ..
prompt=`cat ./testsuitescript/prompt.txt|sed ':a;N;$!ba;s/\n/\\\n/g'|sed 's/\"/\\\"/g'`
echo "${prompt}"
#convert_code "$prompt" "$req_file" 
convert_code "$prompt" "$file_content"|sed -e 's/\\n/\n/g' -e 's/\\\"/\"/g' -e 's/\\t/	/g'
