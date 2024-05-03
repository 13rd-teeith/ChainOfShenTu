from http.server import SimpleHTTPRequestHandler
import http.server

class CORSRequestHandler(SimpleHTTPRequestHandler):
    def end_headers(self):
        self.send_header("Access-Control-Allow-Origin", "*")
        SimpleHTTPRequestHandler.end_headers(self)

if __name__ == '__main__':
    PORT = 8000
    Handler = CORSRequestHandler
    with http.server.ThreadingHTTPServer(("", PORT), Handler) as httpd:
        print("Serving at port", PORT)
        httpd.serve_forever()