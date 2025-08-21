// API Configuration
const API_BASE_URL = "http://localhost:8080/api"

// Global state
let currentPage = 0
let pageSize = 15
let totalPages = 0
let totalRecipes = 0
let currentFilters = {}

// Initialize the application
document.addEventListener("DOMContentLoaded", () => {
  loadRecipes()
  setupEventListeners()
})

// Setup event listeners
function setupEventListeners() {
  // Search input with debounce
  let searchTimeout
  document.getElementById("searchInput").addEventListener("input", () => {
    clearTimeout(searchTimeout)
    searchTimeout = setTimeout(() => {
      applyFilters()
    }, 500)
  })
}

// Load recipes from backend
async function loadRecipes() {
  try {
    showLoading(true)
    hideError()
    hideNoResults()

    // Build query parameters
    const params = new URLSearchParams({
      page: currentPage + 1, // Backend expects 1-based pagination
      size: pageSize,
      ...currentFilters,
    })

    console.log("[v0] Fetching recipes from:", `${API_BASE_URL}/recipes?${params}`)

    const response = await fetch(`${API_BASE_URL}/recipes?${params}`)

    if (!response.ok) {
      throw new Error(`HTTP error! status: ${response.status}`)
    }

    const data = await response.json()
    console.log("[v0] Received data:", data)

    // Update global state
    totalRecipes = data.total || 0
    totalPages = data.totalPages || 0
    currentPage = (data.page || 1) - 1 // Convert back to 0-based

    if (data.data && data.data.length > 0) {
      displayRecipes(data.data)
      updatePagination()
      hideNoResults()
    } else {
      showNoResults()
    }
  } catch (error) {
    console.error("[v0] Error loading recipes:", error)
    showError()
  } finally {
    showLoading(false)
  }
}

// Display recipes in table
function displayRecipes(recipes) {
  const tbody = document.getElementById("recipesTableBody")
  tbody.innerHTML = ""

  recipes.forEach((recipe) => {
    const row = document.createElement("tr")
    row.onclick = () => showRecipeDetails(recipe)
    row.style.cursor = "pointer"

    row.innerHTML = `
            <td>${recipe.title || "N/A"}</td>
            <td>${recipe.cuisine || "N/A"}</td>
            <td>${recipe.rating ? recipe.rating.toFixed(1) + " ⭐" : "N/A"}</td>
            <td>${recipe.total_time ? recipe.total_time + " min" : "N/A"}</td>
            <td>${recipe.serves || "N/A"}</td>
        `

    tbody.appendChild(row)
  })

  document.getElementById("recipesContainer").style.display = "block"
}

// Show recipe details in drawer
function showRecipeDetails(recipe) {
  document.getElementById("drawerTitle").textContent = recipe.title || "N/A"
  document.getElementById("drawerCuisine").textContent = recipe.cuisine || "N/A"
  document.getElementById("drawerDescription").textContent = recipe.description || "No description available"
  document.getElementById("drawerTotalTime").textContent = recipe.total_time ? recipe.total_time + " minutes" : "N/A"
  document.getElementById("drawerPrepTime").textContent = recipe.prep_time ? recipe.prep_time + " minutes" : "N/A"
  document.getElementById("drawerCookTime").textContent = recipe.cook_time ? recipe.cook_time + " minutes" : "N/A"

  // Display nutrition information
  const nutritionGrid = document.getElementById("nutritionGrid")
  nutritionGrid.innerHTML = ""

  const nutritionData = [
    { label: "Calories", value: recipe.calories, unit: "kcal" },
    { label: "Protein", value: recipe.protein_content, unit: "g" },
    { label: "Carbohydrates", value: recipe.carbohydrate_content, unit: "g" },
    { label: "Fat", value: recipe.fat_content, unit: "g" },
    { label: "Fiber", value: recipe.fiber_content, unit: "g" },
    { label: "Sugar", value: recipe.sugar_content, unit: "g" },
    { label: "Sodium", value: recipe.sodium_content, unit: "mg" },
    { label: "Cholesterol", value: recipe.cholesterol_content, unit: "mg" },
  ]

  nutritionData.forEach((item) => {
    if (item.value !== null && item.value !== undefined) {
      const nutritionItem = document.createElement("div")
      nutritionItem.className = "nutrition-item"
      nutritionItem.innerHTML = `
                <span class="nutrition-label">${item.label}:</span>
                <span class="nutrition-value">${item.value}${item.unit}</span>
            `
      nutritionGrid.appendChild(nutritionItem)
    }
  })

  document.getElementById("recipeDrawer").classList.add("open")
}

// Close recipe drawer
function closeDrawer() {
  document.getElementById("recipeDrawer").classList.remove("open")
}

// Toggle time details
function toggleTimeDetails() {
  const breakdown = document.querySelector(".time-breakdown")
  const icon = document.querySelector(".expand-icon")

  breakdown.classList.toggle("hidden")
  icon.textContent = breakdown.classList.contains("hidden") ? "▼" : "▲"
}

// Apply filters and search
function applyFilters() {
  const searchTerm = document.getElementById("searchInput").value.trim()
  const cuisine = document.getElementById("cuisineFilter").value
  const maxCalories = document.getElementById("maxCaloriesFilter").value
  const maxTime = document.getElementById("maxTimeFilter").value
  const minRating = document.getElementById("minRatingFilter").value

  currentFilters = {}

  if (searchTerm) currentFilters.search = searchTerm
  if (cuisine) currentFilters.cuisine = cuisine
  if (maxCalories) currentFilters.maxCalories = maxCalories
  if (maxTime) currentFilters.maxTime = maxTime
  if (minRating) currentFilters.minRating = minRating

  currentPage = 0 // Reset to first page
  loadRecipes()
}

// Pagination functions
function changePage(direction) {
  const newPage = currentPage + direction
  if (newPage >= 0 && newPage < totalPages) {
    currentPage = newPage
    loadRecipes()
  }
}

function changePageSize() {
  pageSize = Number.parseInt(document.getElementById("pageSize").value)
  currentPage = 0 // Reset to first page
  loadRecipes()
}

function updatePagination() {
  const startItem = currentPage * pageSize + 1
  const endItem = Math.min((currentPage + 1) * pageSize, totalRecipes)

  document.getElementById("paginationInfo").textContent = `Showing ${startItem}-${endItem} of ${totalRecipes} recipes`

  // Update pagination controls
  document.getElementById("prevBtn").disabled = currentPage === 0
  document.getElementById("nextBtn").disabled = currentPage >= totalPages - 1

  // Update page numbers
  const pageNumbers = document.getElementById("pageNumbers")
  pageNumbers.innerHTML = ""

  const startPage = Math.max(0, currentPage - 2)
  const endPage = Math.min(totalPages - 1, currentPage + 2)

  for (let i = startPage; i <= endPage; i++) {
    const pageBtn = document.createElement("button")
    pageBtn.textContent = i + 1
    pageBtn.className = i === currentPage ? "active" : ""
    pageBtn.onclick = () => {
      currentPage = i
      loadRecipes()
    }
    pageNumbers.appendChild(pageBtn)
  }
}

// UI state management functions
function showLoading(show) {
  document.getElementById("loading").classList.toggle("hidden", !show)
  document.getElementById("recipesContainer").style.display = show ? "none" : "block"
}

function showError() {
  document.getElementById("errorMessage").classList.remove("hidden")
  document.getElementById("recipesContainer").style.display = "none"
}

function hideError() {
  document.getElementById("errorMessage").classList.add("hidden")
}

function showNoResults() {
  document.getElementById("noResults").classList.remove("hidden")
  document.getElementById("recipesContainer").style.display = "none"
}

function hideNoResults() {
  document.getElementById("noResults").classList.add("hidden")
}
