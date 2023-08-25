const dropArea = document.getElementById('drop-area');
const fileInfo = document.getElementById('file-info');

dropArea.addEventListener('dragenter', preventDefaults, false);
dropArea.addEventListener('dragover', preventDefaults, false);
dropArea.addEventListener('dragleave', preventDefaults, false);
dropArea.addEventListener('drop', preventDefaults, false);

dropArea.addEventListener('dragenter', highlight, false);
dropArea.addEventListener('dragover', highlight, false);
dropArea.addEventListener('dragleave', unhighlight, false);
dropArea.addEventListener('drop', unhighlight, false);

dropArea.addEventListener('drop', handleDrop, false);

function preventDefaults(event) {
    event.preventDefault();
    event.stopPropagation();
}

function highlight() {
    dropArea.classList.add('highlight');
}

function unhighlight() {
    dropArea.classList.remove('highlight');
}

function handleDrop(event) {
    const files = event.dataTransfer.files;
//    console.log("dropArea: "+dropArea);
//    console.log("Files: "+files);
//    console.log("fileInfo: "+fileInfo);
    if (files.length > 0) {
        const fileName = files[0].name;
        fileInfo.style.display = 'block';
        fileInfo.querySelector('p').textContent = 'File name: ' + fileName;
    }
}
