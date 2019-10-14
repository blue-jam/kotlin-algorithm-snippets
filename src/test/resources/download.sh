#!/bin/bash

FILE_NAME=testcases.csv
TEST_CASE_LIST_FILE=$(cd $(dirname $0); pwd)/$FILE_NAME
TEST_CASE_DIR=build/test-cases

while read row; do
  dst_dir=$(echo "${row}" | cut -d , -f 2)
  url=$(echo "${row}" | cut -d , -f 3)
  oj d -a -d "$TEST_CASE_DIR/$dst_dir" "$url"
done < "$TEST_CASE_LIST_FILE"
