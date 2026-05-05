# Postman Mock Data — No DTOs

Base URL (local): http://localhost:8080

---

## Users

Create (POST /users/create)

```json
{
  "name": "Alice Example",
  "email": "alice@example.com",
  "password": "password123"
}
```

Get all (GET /users/all)

Get by id (GET /users/{id})

Update (PUT /users/{id}/update)

```json
{
  "name": "Alice Updated",
  "email": "alice.updated@example.com",
  "password": "newpass456"
}
```

Delete (DELETE /users/{id}/delete)

---

## Templates

Create (POST /templates/create)

```json
{
  "name": "Two-panel Split",
  "imgUrl": "https://example.com/templates/two-panel.png",
  "category": "funny",
  "keyword": "split"
}
```

Get all (GET /templates/all)

Get by id (GET /templates/{id})

Update (PUT /templates/{id}/update)

```json
{
  "name": "Two-panel Split Updated",
  "imgUrl": "https://example.com/templates/two-panel-v2.png",
  "category": "satire",
  "keyword": "split"
}
```

Delete (DELETE /templates/{id}/delete)

---

## Trending

Create (POST /trending/create)

```json
{
  "category": "music",
  "source": "twitter",
  "popularityScore": 87.5,
  "keyword": "chart-topper"
}
```

Get all (GET /trending/all)

Get by id (GET /trending/{id})

Update (PUT /trending/{id}/update)

```json
{
  "category": "memes",
  "source": "reddit",
  "popularityScore": 92.3,
  "keyword": "dank"
}
```

Delete (DELETE /trending/{id}/delete)

---

## Memes

Create (POST /memes/create)

Notes:
- `user` is a nested object referencing an existing user by `userId` — include only the `userId` field to associate.
- `template` is a nested object referencing existing template by `templateId`.
- `trends` can be an array of Trending objects with only `trendingId`.

Example payload:

```json
{
  "imgUrl": "https://example.com/memes/1.png",
  "caption": "When code compiles on first try",
  "genre": "programming",
  "user": { "userId": 1 },
  "template": { "templateId": 1 },
  "trends": [ { "trendingId": 1 }, { "trendingId": 2 } ]
}
```

Get all (GET /memes/all)

Get by id (GET /memes/{id})

Update (PUT /memes/{id}/update)

```json
{
  "imgUrl": "https://example.com/memes/1-v2.png",
  "caption": "When the tests pass",
  "genre": "programming",
  "user": { "userId": 1 },
  "template": { "templateId": 1 },
  "trends": [ { "trendingId": 2 } ]
}
```

Delete (DELETE /memes/{id}/delete)

---

## Quick Postman tips

- Use `POST` to create resources; the response `Location` header contains the created resource URL.
- For `PUT` update calls send the JSON body (no DTOs required) and ensure the path `id` matches the existing resource id.
- To test relationships, create `User`, `Template`, and `Trending` first; then use their numeric ids when creating a `Meme`.
- If you run locally and don't want DB persistence, you can set `spring.jpa.hibernate.ddl-auto=create-drop` in `application.properties` for ephemeral schema during testing.

---

## Example request order to fully test the API

1. POST /users/create -> create a user (note `userId` from Location or response)
2. POST /templates/create -> create a template
3. POST /trending/create -> create one or more trends
4. POST /memes/create -> create a meme referencing the ids from steps 1-3
5. GET /memes/all, /users/all, /templates/all, /trending/all
6. PUT /memes/{id}/update -> update meme
7. DELETE endpoints to clean up
