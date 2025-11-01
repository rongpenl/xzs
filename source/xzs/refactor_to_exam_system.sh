#!/bin/bash

# Script to refactor package from com.mindskip.math to com.exam.system

BASE_DIR="/Users/rongpengli/Documents/GitHub/xzs/source/xzs/src/main/java"
OLD_PACKAGE="com.mindskip.math"
NEW_PACKAGE="com.exam.system"

# Create new package directory structure
mkdir -p "$BASE_DIR/com/exam/system"

# Move all files from com.mindskip.math to com.exam.system
if [ -d "$BASE_DIR/com/mindskip/math" ]; then
    echo "Moving files from $OLD_PACKAGE to $NEW_PACKAGE..."
    mv "$BASE_DIR/com/mindskip/math"/* "$BASE_DIR/com/exam/system/"

    # Remove empty directories
    rmdir "$BASE_DIR/com/mindskip/math" 2>/dev/null || true
    rmdir "$BASE_DIR/com/mindskip" 2>/dev/null || true

    echo "Files moved successfully."
else
    echo "Source directory $BASE_DIR/com/mindskip/math not found."
fi

# Update package declarations and import statements in all Java files
echo "Updating package declarations and import statements..."
find "$BASE_DIR/com/exam/system" -name "*.java" -type f | while read file; do
    # Update package declaration
    sed -i '' "s/^package $OLD_PACKAGE/package $NEW_PACKAGE/" "$file"

    # Update import statements
    sed -i '' "s/import $OLD_PACKAGE/import $NEW_PACKAGE/g" "$file"

    echo "Updated: $file"
done

echo "Package refactoring from $OLD_PACKAGE to $NEW_PACKAGE completed."