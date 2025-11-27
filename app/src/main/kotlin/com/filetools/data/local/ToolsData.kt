package com.filetools.data.local

import com.filetools.R
import com.filetools.data.model.Category
import com.filetools.data.model.Tool

object ToolsData {
    
    val allTools = listOf(
        // Convert From eBook
        Tool("epub_to_pdf", "EPUB to PDF", "Convert EPUB files to PDF format", "convert_from_ebook", "epub"),
        Tool("mobi_to_pdf", "MOBI to PDF", "Convert MOBI files to PDF format", "convert_from_ebook", "mobi"),
        Tool("azw_to_pdf", "AZW to PDF", "Convert AZW files to PDF format", "convert_from_ebook", "azw"),
        Tool("fb2_to_pdf", "FB2 to PDF", "Convert FB2 files to PDF format", "convert_from_ebook", "fb2"),
        Tool("lit_to_pdf", "LIT to PDF", "Convert LIT files to PDF format", "convert_from_ebook", "lit"),
        Tool("pdb_to_pdf", "PDB to PDF", "Convert PDB files to PDF format", "convert_from_ebook", "pdb"),
        
        // Convert To eBook
        Tool("pdf_to_epub", "PDF to EPUB", "Convert PDF files to EPUB format", "convert_to_ebook", "epub"),
        Tool("pdf_to_mobi", "PDF to MOBI", "Convert PDF files to MOBI format", "convert_to_ebook", "mobi"),
        Tool("pdf_to_azw", "PDF to AZW", "Convert PDF files to AZW format", "convert_to_ebook", "azw"),
        Tool("pdf_to_fb2", "PDF to FB2", "Convert PDF files to FB2 format", "convert_to_ebook", "fb2"),
        Tool("pdf_to_lit", "PDF to LIT", "Convert PDF files to LIT format", "convert_to_ebook", "lit"),
        Tool("pdf_to_pdb", "PDF to PDB", "Convert PDF files to PDB format", "convert_to_ebook", "pdb"),
        
        // Converter
        Tool("pdf_to_word", "PDF to Word", "Convert PDF to Word documents", "converter", "word"),
        Tool("pdf_to_excel", "PDF to Excel", "Convert PDF to Excel spreadsheets", "converter", "excel"),
        Tool("pdf_to_powerpoint", "PDF to PowerPoint", "Convert PDF to PowerPoint presentations", "converter", "powerpoint"),
        Tool("word_to_pdf", "Word to PDF", "Convert Word documents to PDF", "converter", "word"),
        Tool("excel_to_pdf", "Excel to PDF", "Convert Excel spreadsheets to PDF", "converter", "excel"),
        Tool("powerpoint_to_pdf", "PowerPoint to PDF", "Convert PowerPoint presentations to PDF", "converter", "powerpoint"),
        Tool("image_to_pdf", "Image to PDF", "Convert images to PDF format", "converter", "image"),
        
        // GIF Tools
        Tool("gif_maker", "GIF Maker", "Create GIFs from images or videos", "gif_tools", "gif"),
        Tool("gif_optimizer", "GIF Optimizer", "Reduce GIF file size without quality loss", "gif_tools", "gif"),
        
        // ZIP Tools
        Tool("zip_creator", "ZIP Creator", "Create ZIP archives from files", "zip_tools", "zip"),
        Tool("zip_extractor", "ZIP Extractor", "Extract files from ZIP archives", "zip_tools", "zip"),
        
        // Others
        Tool("file_renamer", "File Renamer", "Batch rename multiple files", "others", "rename"),
        Tool("duplicate_finder", "Duplicate Finder", "Find and remove duplicate files", "others", "duplicate"),
        Tool("file_comparator", "File Comparator", "Compare two files for differences", "others", "compare"),
        Tool("metadata_editor", "Metadata Editor", "Edit file metadata and properties", "others", "metadata"),
        
        // Optimize Images
        Tool("jpeg_optimizer", "JPEG Optimizer", "Optimize JPEG images for web", "optimize_images", "jpeg"),
        Tool("png_optimizer", "PNG Optimizer", "Optimize PNG images for web", "optimize_images", "png"),
        Tool("webp_optimizer", "WebP Optimizer", "Optimize WebP images for web", "optimize_images", "webp"),
        Tool("gif_optimizer_img", "GIF Optimizer", "Optimize GIF images for web", "optimize_images", "gif"),
        Tool("svg_optimizer", "SVG Optimizer", "Optimize SVG files for web", "optimize_images", "svg"),
        Tool("tiff_optimizer", "TIFF Optimizer", "Optimize TIFF images for web", "optimize_images", "tiff"),
        Tool("bmp_optimizer", "BMP Optimizer", "Optimize BMP images for web", "optimize_images", "bmp"),
        
        // Convert Images
        Tool("jpg_to_png", "JPG to PNG", "Convert JPG images to PNG format", "convert_images", "jpg"),
        Tool("png_to_jpg", "PNG to JPG", "Convert PNG images to JPG format", "convert_images", "png"),
        Tool("webp_to_png", "WebP to PNG", "Convert WebP images to PNG format", "convert_images", "webp"),
        Tool("png_to_webp", "PNG to WebP", "Convert PNG images to WebP format", "convert_images", "webp"),
        Tool("gif_to_mp4", "GIF to MP4", "Convert GIF animations to MP4 video", "convert_images", "gif"),
        Tool("mp4_to_gif", "MP4 to GIF", "Convert MP4 video to GIF animation", "convert_images", "gif"),
        
        // Edit Images
        Tool("crop_image", "Crop Image", "Crop images to desired dimensions", "edit_images", "crop"),
        Tool("resize_image", "Resize Image", "Resize images to specific dimensions", "edit_images", "resize"),
        Tool("rotate_image", "Rotate Image", "Rotate images by specified angles", "edit_images", "rotate"),
        Tool("flip_image", "Flip Image", "Flip images horizontally or vertically", "edit_images", "flip"),
        Tool("add_watermark", "Add Watermark", "Add text or image watermarks", "edit_images", "watermark"),
        Tool("remove_background", "Remove Background", "Remove background from images", "edit_images", "background"),
        
        // Optimize PDF
        Tool("pdf_compressor", "PDF Compressor", "Compress PDF files to reduce size", "optimize_pdf", "compress"),
        
        // Merge & Split PDF
        Tool("merge_pdf", "Merge PDF", "Combine multiple PDFs into one", "merge_split_pdf", "merge"),
        Tool("split_pdf", "Split PDF", "Split PDF into multiple files", "merge_split_pdf", "split"),
        Tool("extract_pages", "Extract Pages", "Extract specific pages from PDF", "merge_split_pdf", "extract"),
        
        // View & Edit PDF
        Tool("pdf_viewer", "PDF Viewer", "View PDF documents", "view_edit_pdf", "view"),
        Tool("pdf_editor", "PDF Editor", "Edit PDF content and structure", "view_edit_pdf", "edit"),
        Tool("add_annotations", "Add Annotations", "Add notes and annotations to PDF", "view_edit_pdf", "annotate"),
        Tool("fill_forms", "Fill Forms", "Fill PDF forms electronically", "view_edit_pdf", "forms"),
        Tool("add_signature", "Add Signature", "Add digital signatures to PDF", "view_edit_pdf", "signature"),
        Tool("password_protect", "Password Protect", "Add password protection to PDF", "view_edit_pdf", "password"),
        Tool("remove_password", "Remove Password", "Remove password protection from PDF", "view_edit_pdf", "unlock"),
        Tool("organize_pages", "Organize Pages", "Reorder and organize PDF pages", "view_edit_pdf", "organize"),
        
        // Convert To PDF
        Tool("word_to_pdf_conv", "Word to PDF", "Convert Word documents to PDF", "convert_to_pdf", "word"),
        Tool("excel_to_pdf_conv", "Excel to PDF", "Convert Excel spreadsheets to PDF", "convert_to_pdf", "excel"),
        Tool("powerpoint_to_pdf_conv", "PowerPoint to PDF", "Convert PowerPoint to PDF", "convert_to_pdf", "powerpoint"),
        Tool("image_to_pdf_conv", "Image to PDF", "Convert images to PDF format", "convert_to_pdf", "image"),
        Tool("html_to_pdf", "HTML to PDF", "Convert HTML pages to PDF", "convert_to_pdf", "html"),
        Tool("text_to_pdf", "Text to PDF", "Convert text files to PDF", "convert_to_pdf", "text"),
        
        // Convert From PDF
        Tool("pdf_to_word_conv", "PDF to Word", "Convert PDF to Word documents", "convert_from_pdf", "word"),
        Tool("pdf_to_excel_conv", "PDF to Excel", "Convert PDF to Excel spreadsheets", "convert_from_pdf", "excel"),
        Tool("pdf_to_powerpoint_conv", "PDF to PowerPoint", "Convert PDF to PowerPoint", "convert_from_pdf", "powerpoint"),
        Tool("pdf_to_image", "PDF to Image", "Convert PDF pages to images", "convert_from_pdf", "image"),
        Tool("pdf_to_html", "PDF to HTML", "Convert PDF to HTML format", "convert_from_pdf", "html"),
        Tool("pdf_to_text", "PDF to Text", "Extract text from PDF documents", "convert_from_pdf", "text"),
        
        // PDF Security
        Tool("encrypt_pdf", "Encrypt PDF", "Add encryption to PDF files", "pdf_security", "encrypt"),
        Tool("decrypt_pdf", "Decrypt PDF", "Remove encryption from PDF files", "pdf_security", "decrypt")
    )
    
    val categories = listOf(
        Category(
            id = "convert_from_ebook",
            name = "Convert From eBook",
            description = "Convert various eBook formats to PDF",
            icon = "ebook_from",
            gradientStart = "#FF6B6B",
            gradientEnd = "#FF8E8E",
            tools = allTools.filter { it.categoryId == "convert_from_ebook" }
        ),
        Category(
            id = "convert_to_ebook",
            name = "Convert To eBook",
            description = "Convert PDF to various eBook formats",
            icon = "ebook_to",
            gradientStart = "#4ECDC4",
            gradientEnd = "#44A39D",
            tools = allTools.filter { it.categoryId == "convert_to_ebook" }
        ),
        Category(
            id = "converter",
            name = "Converter",
            description = "Convert between document formats",
            icon = "converter",
            gradientStart = "#45B7D1",
            gradientEnd = "#2196F3",
            tools = allTools.filter { it.categoryId == "converter" }
        ),
        Category(
            id = "gif_tools",
            name = "GIF Tools",
            description = "Create and optimize GIF animations",
            icon = "gif",
            gradientStart = "#96CEB4",
            gradientEnd = "#88D8B0",
            tools = allTools.filter { it.categoryId == "gif_tools" }
        ),
        Category(
            id = "zip_tools",
            name = "ZIP Tools",
            description = "Create and extract ZIP archives",
            icon = "zip",
            gradientStart = "#FFEAA7",
            gradientEnd = "#FDCB6E",
            tools = allTools.filter { it.categoryId == "zip_tools" }
        ),
        Category(
            id = "others",
            name = "Others",
            description = "Additional file management tools",
            icon = "others",
            gradientStart = "#DDA0DD",
            gradientEnd = "#BA55D3",
            tools = allTools.filter { it.categoryId == "others" }
        ),
        Category(
            id = "optimize_images",
            name = "Optimize Images",
            description = "Compress and optimize image files",
            icon = "optimize_images",
            gradientStart = "#98D8C8",
            gradientEnd = "#7FCDCD",
            tools = allTools.filter { it.categoryId == "optimize_images" }
        ),
        Category(
            id = "convert_images",
            name = "Convert Images",
            description = "Convert between image formats",
            icon = "convert_images",
            gradientStart = "#FFB6C1",
            gradientEnd = "#FF91A4",
            tools = allTools.filter { it.categoryId == "convert_images" }
        ),
        Category(
            id = "edit_images",
            name = "Edit Images",
            description = "Edit and enhance images",
            icon = "edit_images",
            gradientStart = "#87CEEB",
            gradientEnd = "#6BB6D6",
            tools = allTools.filter { it.categoryId == "edit_images" }
        ),
        Category(
            id = "optimize_pdf",
            name = "Optimize PDF",
            description = "Compress and optimize PDF files",
            icon = "optimize_pdf",
            gradientStart = "#F0E68C",
            gradientEnd = "#DAA520",
            tools = allTools.filter { it.categoryId == "optimize_pdf" }
        ),
        Category(
            id = "merge_split_pdf",
            name = "Merge & Split PDF",
            description = "Merge and split PDF documents",
            icon = "merge_split_pdf",
            gradientStart = "#FFA07A",
            gradientEnd = "#FA8072",
            tools = allTools.filter { it.categoryId == "merge_split_pdf" }
        ),
        Category(
            id = "view_edit_pdf",
            name = "View & Edit PDF",
            description = "View and edit PDF documents",
            icon = "view_edit_pdf",
            gradientStart = "#20B2AA",
            gradientEnd = "#48D1CC",
            tools = allTools.filter { it.categoryId == "view_edit_pdf" }
        ),
        Category(
            id = "convert_to_pdf",
            name = "Convert To PDF",
            description = "Convert various formats to PDF",
            icon = "convert_to_pdf",
            gradientStart = "#FF69B4",
            gradientEnd = "#FF1493",
            tools = allTools.filter { it.categoryId == "convert_to_pdf" }
        ),
        Category(
            id = "convert_from_pdf",
            name = "Convert From PDF",
            description = "Convert PDF to other formats",
            icon = "convert_from_pdf",
            gradientStart = "#B0C4DE",
            gradientEnd = "#778899",
            tools = allTools.filter { it.categoryId == "convert_from_pdf" }
        ),
        Category(
            id = "pdf_security",
            name = "PDF Security",
            description = "Secure and protect PDF files",
            icon = "pdf_security",
            gradientStart = "#F4A460",
            gradientEnd = "#D2691E",
            tools = allTools.filter { it.categoryId == "pdf_security" }
        )
    )
}
