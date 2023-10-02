#!/usr/bin/bash
#set -x
convert_code()
{
#echo $1
curl --silent $url \
		-H "Authorization: Bearer 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" \
		-H "Content-Type: application/json" \
		-d "{
		     \"model\": \"gpt-3.5-turbo\",
	    	     \"messages\": [{\"role\": \"user\", \"content\": \"Provide an SOAPUI XML project file to test the below requirement with all possible testcases: A c# code \n\n which accept the Account Number as input and provide output in REST format with all the details coming from the stub, it also intreact with one backend stub which is SOAP based and accepts Request:\"}],
		     \"temperature\": 0.7
	            }" \
		|awk -F\" '
		$2=="content" {
				print $0
				}' \
		|awk -F\"content\": '
			{
			   print $2
		   	}'
}
​
#url=https://jsonplaceholder.typicode.com/posts
url=https://api.openai.com/v1/chat/completions
	cs_file=`cat $1|tr '\r\n' ' '|sed 's/\"/\\\"/g'`
	soap_file=`cat $2|tr '\r\n' ' '|sed 's/\"/\\\"/g'`
	convert_code "$cs_file" "$soap_file"  
