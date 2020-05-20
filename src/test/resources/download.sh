#!/bin/bash

TEST_CASE_DIR=build/test-cases

for file in $(find src/test/kotlin -name *.kt) ; do
  url="$(sed -e 's/^.*"\(https\?:\/\/.*\)"/\1/ ; t ; d' "$file")"
  if [[ -z ${url} ]] ; then
    continue
  fi

  dst_dir=$(echo -n "$url" | md5sum | sed 's/ .*//')
  oj d -a -d "$TEST_CASE_DIR/$dst_dir" "$url"
done
