window.onload = function () {
  const source = new SSE("/user/watch/stream");
  source.addEventListener('message', function (e) {
    if (e.data) {
      const payload = JSON.parse(e.data);
      $("#data").append("<p>" + payload.action.replace(/\n/g, "<br>") + "</p>");
    }
  });
  source.stream();
};