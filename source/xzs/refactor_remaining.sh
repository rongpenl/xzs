#!/bin/bash

# Script to refactor remaining package names from com.mindskip.xzs to com.mindskip.math

BASE_DIR="/Users/rongpengli/Documents/GitHub/xzs/source/xzs/src/main/java"
OLD_PKG="com.mindskip.xzs"
NEW_PKG="com.mindskip.math"

# Function to move and update a single file
refactor_file() {
    local file_path=$1
    local relative_path=$(echo $file_path | sed "s|$BASE_DIR/com/mindskip/xzs/||")
    local target_path="$BASE_DIR/com/mindskip/math/$relative_path"

    # Create target directory
    local target_dir=$(dirname "$target_path")
    mkdir -p "$target_dir"

    # Move file
    mv "$file_path" "$target_path"

    # Update package declaration
    sed -i '' "s/package $OLD_PKG/package $NEW_PKG/g" "$target_path"

    # Update import statements
    sed -i '' "s/import $OLD_PKG/import $NEW_PKG/g" "$target_path"

    echo "Refactored: $file_path -> $target_path"
}

# Process remaining files
echo "Processing remaining files..."

# Process listener files
for file in $BASE_DIR/com/mindskip/xzs/listener/*.java; do
    refactor_file "$file"
done

# Process context files
for file in $BASE_DIR/com/mindskip/xzs/context/*.java; do
    refactor_file "$file"
done

# Process exception files
for file in $BASE_DIR/com/mindskip/xzs/exception/*.java; do
    refactor_file "$file"
done

# Process event files
for file in $BASE_DIR/com/mindskip/xzs/event/*.java; do
    refactor_file "$file"
done

# Process base files
for file in $BASE_DIR/com/mindskip/xzs/base/*.java; do
    refactor_file "$file"
done

echo "Remaining files refactoring completed!"