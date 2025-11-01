#!/bin/bash

# Script to update package declarations and import statements in test files

BASE_DIR="/Users/rongpengli/Documents/GitHub/xzs/source/xzs/src/test/java"
OLD_PACKAGE="com.mindskip.xzs"
NEW_PACKAGE="com.exam.system"

echo "Updating package declarations and import statements in test files..."

# Update package declarations and import statements in all test Java files
find "$BASE_DIR/com/exam/system" -name "*.java" -type f | while read file; do
    # Update package declaration
    sed -i '' "s/^package $OLD_PACKAGE/package $NEW_PACKAGE/" "$file"

    # Update import statements
    sed -i '' "s/import $OLD_PACKAGE/import $NEW_PACKAGE/g" "$file"

    echo "Updated: $file"
done

echo "All test files have been updated with new package structure."