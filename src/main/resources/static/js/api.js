// GET
function getPost() {

    if ( !validate() ) {
        return;
    }

    // http://localhost:8080/posts/1
    fetch(`http://localhost:8080/posts/${getSequence()}`)
        .then(resp => resp.json())
        .then(data => {
            console.log(data);

            refresh(
                data.title,
                data.author,
                data.contents
            );
            alert('가장 최신 글로 데이터 갱신!');

        })
        .catch(
            err => {
                alert('에러발생!');
                console.log(err);
            }
        )

}

// POST
function createPost() {

    // JSON
    // Content-Type: application/json
    const data = getPostValues();

    fetch('http://localhost:8080/posts', {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body : JSON.stringify(data)
    })
    .then(
        resp => resp.json()
    )
    .then(data => {
        console.log(data);
        updateSequence(data);
        alert('게시물 등록 완료!');
        clearPostBody();
    })
    .catch(
        err => console.log(err)
    );

}

function updatePost() {

    if ( !validate() ) {
        return;
    }

    const data = getPatchValues();

    fetch(`http://localhost:8080/posts/${getSequence()}`, {
        method: "PATCH",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    })
    .then(resp => resp.json())
    .then(data => {

        console.log(data);

        refresh(
            data.title,
            data.author,
            data.contents
        );
        updateSequence(data.id);
        alert('게시물 수정 완료!');
        clearPatchBody();


    })
    .catch(
        err => {
            alert('에러발생!');
            console.log(err);
        }
    )


}

function removePost() {

    if ( !validate() ) {
        return;
    }

    fetch(`http://localhost:8080/posts/${getSequence()}`, {
        method: "DELETE"
    })
    .then(resp => {
        console.log(resp);
        refresh('Title', 'Author', 'Content');
        syncSequence();
        alert('마지막 글이 삭제되었습니다!');
    })
    .catch();


}


