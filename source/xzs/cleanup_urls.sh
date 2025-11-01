#!/bin/bash

# Script to replace all URLs with blank URLs (example.com) and clean up remaining references

BASE_DIR="/Users/rongpengli/Documents/GitHub/xzs/source/xzs"

# Replace URLs in JavaScript files
echo "Replacing URLs in JavaScript files..."
find "$BASE_DIR/src/main/resources/static" -name "*.js" -type f | while read file; do
    # Replace mindskip.net URLs with example.com
    sed -i '' "s|https://www.mindskip.net|https://example.com|g" "$file"
    sed -i '' "s|http://xzs.file.mindskip.net|https://example.com|g" "$file"
    sed -i '' "s|https://www.mindskip.net:999|https://example.com|g" "$file"
    sed -i '' "s|https://gitee.com/mindskip|https://example.com|g" "$file"
    sed -i '' "s|https://ke.qq.com/course/3614230|https://example.com|g" "$file"

    echo "Updated: $file"
done

# Replace URLs in HTML files
echo "Replacing URLs in HTML files..."
find "$BASE_DIR/src/main/resources/static" -name "*.html" -type f | while read file; do
    # Replace mindskip.net URLs with example.com
    sed -i '' "s|https://www.mindskip.net|https://example.com|g" "$file"
    sed -i '' "s|http://xzs.file.mindskip.net|https://example.com|g" "$file"
    sed -i '' "s|https://www.mindskip.net:999|https://example.com|g" "$file"
    sed -i '' "s|https://gitee.com/mindskip|https://example.com|g" "$file"
    sed -i '' "s|https://ke.qq.com/course/3614230|https://example.com|g" "$file"

    echo "Updated: $file"
done

echo "URL cleanup completed."