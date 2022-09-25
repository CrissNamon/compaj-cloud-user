window.onload = function () {
  const source = new SSE("/user/watch/stream");
  source.addEventListener('message', function (e) {
    if (e.data) {
      const payload = JSON.parse(e.data);
      $(".container").append('<p>' + payload.action + '</p>')
    }
  });
  source.stream();
};