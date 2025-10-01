It needs adaptation for Java/Android context (e.g., Fragments/AsyncTasks vs. ViewModels/XAML; MainActivity.java vs. BleMvxApplication.cs). Here's the updated prompts.md:

```markdown
## Overview
This file contains prompt templates for use with large language models (LLMs) like Grok, ChatGPT, or Claude to analyze, prune, or optimize large codebases, such as this Java Android RFID reader app. These prompts ensure *methodical* traversal ("prospecting"), dependency tracing, safety (error-free builds), and structured chat log outputs (file trees with emojis/notes). Copy/paste into LLM chats, replacing placeholders (e.g., [FEATURE_NAME]) with specifics.

## Prompt Templates

### 1. Initial Big-Picture Pruning Plan
**Prompt**:
Given this large Java Android codebase (~3M+ chars from `repomix-output.txt`), prune deprecated features while keeping core ones (Inventory, Access, Configuration, Connect, Voltage Display). Pruned: [LIST_PRUNED_FEATURES].
Provide a big-picture plan: Analyze the entire repository structure, map preserved vs. pruned features based on UI diffs (e.g., old/new fragment XMLs like fragment_inventory.xml), and trace dependencies recursively (primary: direct files; secondary: navigation/callbacks/bindings; tertiary: shared code/events/platform-specific; quaternary+: resources/tests/docs/edge cases like intents/permissions).
Highlight risks: Intertwined events (e.g., global RFID callbacks in MainActivity.java or CsLibrary4A.java), shared utilities (e.g., in Utility.java/SharedObjects.java), custom views (e.g., in adapters/fragments), resources (strings.xml/styles.xml/images), code comments, unit tests, analytics/logging, and config files (build.gradle).
Suggest order of removal (e.g., isolated fragments first). Ensure 100% build safety with incremental steps: Backups (git branches), global searches ("Find in Files" for feature names/strings), verifications (rebuilds via Gradle, runtime tests on Android emulator/device, log checks for errors/warnings, preserved feature testing). Output: Overview with analysis/comprehension, then high-level per-feature steps with tools (e.g., Android Studio "Find Usages", git commits).

---
**Usage**: Initiates a pruning session by outlining the strategy for removing deprecated features. Use to understand the codebase and plan safe modifications, proactively addressing common risks and gaps from layered dependencies.

### 2. Feature-Specific Pruning Instructions
**Prompt**:
Prune [FEATURE_NAME] from the codebase. Use the `repomix-output.txt` structure for a comprehensive file tree traversal.
Output a file tree with full paths:
- ❌ for changes (deletes/edits).
- ✅ for untouched.
- [NOTE] for details: What to cut (line-by-line/code blocks with examples), why (e.g., prevents compile/runtime errors from deleted refs, avoids global callback leaks), verify (e.g., rebuild—no errors; run app—test preserved features like Inventory for regressions).
Cover all levels exhaustively:
- Primary (direct files/folders, e.g., app/src/main/java/com/csl/cs710ademoapp/fragments/[FEATURE_NAME]Fragment.java).
- Secondary (navigation/callbacks/bindings, e.g., in MainActivity.java or fragment managers; XML references across layouts).
- Tertiary (shared code/events/platform-specific, e.g., global event handlers in MainActivity.java, shared utilities in cslibrary4a/, custom adapters/views in app/src/main/).
- Quaternary+ (resources like strings.xml/styles.xml/images; tests in src/test/; docs/comments via global searches; edge cases like intent stacks/permissions, analytics/logging, config files).
Use global searches ("Find in Files" case-insensitive for [FEATURE_NAME], callbacks, fragments) for strays. Proactively check common gaps: Stray XML bindings, navigation history/intent stacks, global events (e.g., RFID callbacks in AccessTask.java with feature-specific logic), shared utils/constants, platform services/connectors, resources (strings.xml/styles.xml/images/drawable), code comments/inline docs, unit tests/mocks, analytics (e.g., logging feature events), configs (e.g., toggles in build.gradle). Ensure no regressions in preserved features. End with summary, potential issues, and commit suggestion (e.g., "git commit -m 'Pruned [FEATURE_NAME]: Removed files/refs. Verified builds.'").

---
**Usage**: Core template for pruning a specific feature (e.g., "Access Read/Write"). Ensures granular, safe removal with full dependency cleanup, now with explicit proactive gap checks for exhaustive coverage.

### 3. Refining Pruning Output
**Prompt**:
Review my previous pruning instructions for [FEATURE_NAME]. Check depth exhaustively: Did we cover primary/secondary/tertiary/quaternary+? Identify additional gaps like: Stray XML references across layouts, navigation history/intent stacks (e.g., in MainActivity.java or fragment transactions), global event handlers (e.g., RFID callbacks with feature-specific conditions in MainActivity.java or tasks), shared utilities/constants (e.g., in Utility.java/SharedObjects.java), platform-specific connectors/services (e.g., in BluetoothConnector.java), resources (strings.xml, styles in values/, images/drawable), code comments/inline docs (global search for feature names), unit tests/mocks (e.g., in src/test/), analytics/logging (e.g., feature-specific events in logs), configuration files (e.g., toggles in build.gradle).
Refine: Add missed steps (e.g., global searches for feature strings in comments/resources, module build.gradle edits, edge case tests like intents/permissions). Output updated instructions in file tree format with ❌/✅ and [NOTE] prefixes (what/why/verify). Ensure 100% build safety with emphasis on post-prune testing (rebuilds via Gradle, runtime on Android emulator/device, preserved feature verification, logs for warnings/errors).

**Usage**: Iterates on prior outputs to address gaps or refine instructions, ensuring exhaustive coverage with expanded common gap examples drawn from hands-on experience.

---
## Tips for Using These Prompts
- **Context Inclusion**: Always prepend full repo structure (e.g., `repomix-output.txt`) and feature lists (preserved/pruned) for accuracy.
- **Iteration**: If output needs tweaks, follow up with specifics (e.g., "Add more on edge cases like analytics/logging").
- **LLM Choice**: Use Grok for reasoning/large contexts, Claude for structured Markdown, or ChatGPT for quick iterations.
- **Safety**: Backup before applying changes (e.g., `git branch prune-<feature>`); test preserved features post-prune.
```