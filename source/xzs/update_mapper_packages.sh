#!/bin/bash

# Script to update package references in MyBatis mapper XML files

BASE_DIR="/Users/rongpengli/Documents/GitHub/xzs/source/xzs/src/main/resources/mapper"
OLD_PACKAGE="com.mindskip.xzs"
NEW_PACKAGE="com.exam.system"

echo "Updating package references in MyBatis mapper XML files..."

# Update package references in all mapper XML files
find "$BASE_DIR" -name "*.xml" -type f | while read file; do
    # Update namespace and type references
    sed -i '' "s/com\.mindskip\.xzs/com\.exam\.system/g" "$file"

    echo "Updated: $file"
done

echo "All mapper XML files have been updated with new package structure."