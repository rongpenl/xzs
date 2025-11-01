#!/bin/bash

# Script to fix remaining import statements that use fully qualified class names

BASE_DIR="/Users/rongpengli/Documents/GitHub/xzs/source/xzs/src/main/java"
OLD_PACKAGE="com.mindskip.math"
NEW_PACKAGE="com.exam.system"

echo "Fixing remaining import statements..."

# Find and replace all remaining old package references
find "$BASE_DIR/com/exam/system" -name "*.java" -type f | while read file; do
    # Replace fully qualified class names in method signatures and other code
    sed -i '' "s/$OLD_PACKAGE\./$NEW_PACKAGE\./g" "$file"

    echo "Updated: $file"
done

echo "All remaining import statements have been fixed."