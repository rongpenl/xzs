#!/bin/bash

# Script to refactor package names from com.mindskip.xzs to com.mindskip.math

BASE_DIR="/Users/rongpengli/Documents/GitHub/xzs/source/xzs/src/main/java"
OLD_PKG="com.mindskip.xzs"
NEW_PKG="com.mindskip.math"

# Create target directory structure
mkdir -p $BASE_DIR/com/mindskip/math/{domain,service,repository,controller,viewmodel,configuration,utility}

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

# Process files in batches
echo "Starting package refactoring..."

# Process domain files first
echo "Processing domain files..."
find $BASE_DIR/com/mindskip/xzs/domain -name "*.java" | while read file; do
    refactor_file "$file"
done

# Process service files
echo "Processing service files..."
find $BASE_DIR/com/mindskip/xzs/service -name "*.java" | while read file; do
    refactor_file "$file"
done

# Process repository files
echo "Processing repository files..."
find $BASE_DIR/com/mindskip/xzs/repository -name "*.java" | while read file; do
    refactor_file "$file"
done

# Process controller files
echo "Processing controller files..."
find $BASE_DIR/com/mindskip/xzs/controller -name "*.java" | while read file; do
    refactor_file "$file"
done

# Process viewmodel files
echo "Processing viewmodel files..."
find $BASE_DIR/com/mindskip/xzs/viewmodel -name "*.java" | while read file; do
    refactor_file "$file"
done

# Process configuration files
echo "Processing configuration files..."
find $BASE_DIR/com/mindskip/xzs/configuration -name "*.java" | while read file; do
    refactor_file "$file"
done

# Process utility files
echo "Processing utility files..."
find $BASE_DIR/com/mindskip/xzs/utility -name "*.java" | while read file; do
    refactor_file "$file"
done

echo "Package refactoring completed!"